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

    final RestTemplate restTemplate;

    public DrugSearchResponse searchForBrand(String brandName) {

        String searchTerm = "openfda.brand_name:\"{brandName}\"";
        Map<String, String> vars = new HashMap<>();
        vars.put("brandName", brandName);
        return search(searchTerm, vars);
    }

    public DrugSearchResponse searchForManufacturer(String manufacturer) {

        String searchTerm = "openfda.manufacturer_name:\"{manufacturer}\"";
        Map<String, String> vars = new HashMap<>();
        vars.put("manufacturer", manufacturer);
        return search(searchTerm, vars);
    }

    public DrugSearchResponse searchForApplicationNumber(String application_number) {

        String searchTerm = "openfda.application_number:\"{number}\"";
        Map<String, String> vars = new HashMap<>();
        vars.put("application_number", application_number);
        return search(searchTerm, vars);
    }

    public DrugSearchResponse searchForBoth(String manufacturer, String brand) {
        String searchTerm = "openfda.manufacturer_name:\"{manufacturer}\"";
        searchTerm += "+AND+";
        searchTerm += "openfda.brand_name:\"{brandName}\"";

        Map<String, String> vars = new HashMap<>();
        vars.put("manufacturer", manufacturer);
        vars.put("brandName", brand);
        return search(searchTerm, vars);
    }

    private DrugSearchResponse search(String searchTerm, Map<String, String> vars) {
        ResponseEntity<DrugSearchResponse> response
                = restTemplate.getForEntity(searchUrl + searchTerm, DrugSearchResponse.class, vars);
        // TODO check for valid response

        return response.getBody();
    }

}
