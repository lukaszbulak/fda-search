package pl.square.fdasearch.search.application;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import pl.square.fdasearch.search.domain.DrugSearchResponse;

@Service
@AllArgsConstructor
public class SearchService {

    final String searchUrl = "https://api.fda.gov/drug/drugsfda.json?search=";

//    final RestTemplateFactory factory;
    final RestTemplate restTemplate;

    public String searchForBrand(String brandName) {

        String searchTerm = "openfda.brand_name:\"{brandName}\""; // &limit=1
        Map<String, String> vars = new HashMap<>();
        vars.put("brandName", brandName);
        return search(searchTerm, vars).toString();

    }

    public String searchForManufacturer(String manufacturer) {

        String searchTerm = "openfda.manufacturer_name:\"{manufacturer}\""; // &limit=1

        Map<String, String> vars = new HashMap<>();
        vars.put("manufacturer", manufacturer);
        return search(searchTerm, vars).toString();
    }

    private DrugSearchResponse search(String searchTerm, Map<String, String> vars) {
        ResponseEntity<DrugSearchResponse> response
                = restTemplate.getForEntity(searchUrl + searchTerm, DrugSearchResponse.class, vars);
        // TODO check for valid response

        return response.getBody();
    }

    public DrugSearchResponse searchForApplicationNumber(String application_number) {
        // search=openfda.application_number:"FOO+BAR"

        String searchTerm = "openfda.application_number:\"{number}\""; // &limit=1

        Map<String, String> vars = new HashMap<>();
        vars.put("application_number", application_number);
        return search(searchTerm, vars);

    }
}
