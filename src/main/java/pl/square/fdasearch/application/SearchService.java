package pl.square.fdasearch.application;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.square.fdasearch.domain.DrugSearchResponse;

@Service
@AllArgsConstructor
public class SearchService {

    final String searchUrl = "https://api.fda.gov/drug/drugsfda.json?search=";

//    final RestTemplateFactory factory;
    final RestTemplate restTemplate;

    public String searchForBrand(String brandName) {

  //      RestTemplate restTemplate = factory.create();
        String searchTerm = "openfda.brand_name:\""+brandName+"\""; // &limit=1

        // TODO use params here
        ResponseEntity<DrugSearchResponse> response
                = restTemplate.getForEntity(searchUrl + searchTerm, DrugSearchResponse.class);
        // TODO check for valid response

        return response.getBody().toString();
    }
}
