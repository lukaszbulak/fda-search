package pl.square.fdasearch.store.web;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.square.fdasearch.search.application.SearchService;
import pl.square.fdasearch.search.domain.Product;
import pl.square.fdasearch.store.domain.Drug;
import pl.square.fdasearch.store.repository.LocalDrugRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.apache.logging.log4j.util.Strings.isEmpty;
import static org.springframework.http.MediaType.*;

@Controller
@Api(value = "api value", description = "Manage local drug storage")
@AllArgsConstructor
public class LocalStoreController {

    private final SearchService service;

    private final LocalDrugRepository repository;

    @GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> listAll() {

        Iterable<Drug> drugs = repository.findAll();
        List<Drug> drugList = StreamSupport.stream(drugs.spliterator(), false)
                .collect(Collectors.toList());

        return ResponseEntity.ok(drugList.toString());
    }

}
