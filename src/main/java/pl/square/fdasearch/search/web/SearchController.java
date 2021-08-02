package pl.square.fdasearch.search.web;


import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.square.fdasearch.search.application.SearchService;

import static org.apache.logging.log4j.util.Strings.isEmpty;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@Api(value = "api value", description = "Search for drugs in OpenFDA")
@AllArgsConstructor
public class SearchController {

    private final SearchService service;

    @GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> search(@RequestParam(required = false) String manufacturer, @RequestParam(required = false) String brand) {
        if (isEmpty(manufacturer)) {
            if (isEmpty(brand)) {
                return ResponseEntity.badRequest().body("both arguments cannot be empty");
            }
            return ResponseEntity.ok(service.searchForBrand(brand).toString());
        }
        if (isEmpty(brand)) {
            return ResponseEntity.ok(service.searchForManufacturer(manufacturer).toString()); //searchContoller.searchForManufacturer(manufacturer);
        }

        return ResponseEntity.ok(service.searchForBoth(manufacturer, brand).toString());
    }


}
