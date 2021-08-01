package pl.square.fdasearch;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.square.fdasearch.domain.DrugSearchResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class OpenFdaIT {

    @Test
    public void simpleSearchForBrand() {

        RestTemplate restTemplate = new RestTemplate();
        String searchUrl = "https://api.fda.gov/drug/drugsfda.json?search=";
        String brandName = "Tecentriq";
        String searchTerm = "openfda.brand_name:\""+brandName+"\""; // &limit=1

        //ResponseEntity<String> response = restTemplate.getForEntity(searchUrl + searchTerm, String.class);
        ResponseEntity<DrugSearchResponse> response
                = restTemplate.getForEntity(searchUrl + searchTerm, DrugSearchResponse.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(response.getBody());
    }

    @Test
    public void parseResponseTest() throws URISyntaxException, IOException {

        String response = Files.readString(Path.of(getClass().getResource("/response.json").toURI()));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        DrugSearchResponse resp = mapper.readValue(response, DrugSearchResponse.class);
        System.out.println(resp);

    }

}
