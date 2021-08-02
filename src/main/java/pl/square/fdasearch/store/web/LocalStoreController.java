package pl.square.fdasearch.store.web;


import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import pl.square.fdasearch.search.application.SearchService;
import pl.square.fdasearch.search.domain.Product;
import pl.square.fdasearch.store.application.LocalStoreService;
import pl.square.fdasearch.store.domain.Drug;
import pl.square.fdasearch.store.repository.LocalDrugRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.MediaType.*;

@Controller
@Api(value = "api value", description = "Manage local drug storage")
@AllArgsConstructor
public class LocalStoreController {

    private final SearchService searchService;

    private final LocalDrugRepository repository;

    private final LocalStoreService storeService;

    @GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Drug>> listAll() {

        Iterable<Drug> drugs = repository.findAll();
        List<Drug> drugList = StreamSupport.stream(drugs.spliterator(), false)
                .collect(Collectors.toList());

        return ResponseEntity.ok(drugList);
    }

    /** PUT method is idempotent.
     * But here application_number is a key, so calling multiple times will eventually update existing record.
     * @return
     */
    @PutMapping(value = "/store/{application_number}", produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<String> storeDrug(@PathVariable String application_number) {

        // find
        pl.square.fdasearch.search.domain.DrugSearchResponse found = searchService.searchForApplicationNumber(application_number);
        // TODO check is found
        pl.square.fdasearch.search.domain.DrugSearchResult result = found.getResults().get(0);
        // check result

        String modeInfo = storeService.storeLocally(result, application_number);
        return ResponseEntity.ok(modeInfo);
    }


}
