package pl.square.fdasearch.application;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.square.fdasearch.domain.DrugSearchResponse;

import static org.mockito.Mockito.mock;

@SpringBootTest
@EnableAutoConfiguration(exclude= DataSourceAutoConfiguration.class)
public class SearchServiceTest {

    @Mock
    RestTemplate template;

    @Autowired
    SearchService service;

    @Test
    public void searchForBrand() {

        //given:
        ResponseEntity<DrugSearchResponse> resp = (ResponseEntity<DrugSearchResponse>)mock(ResponseEntity.class);
        Mockito.when(template.getForEntity(Mockito.anyString(), Mockito.eq(DrugSearchResponse.class))).thenReturn(resp);

        //when:
        String result = service.searchForBrand("apap");

        //then:
        //!result.isBlank()
        System.out.println(result);

        //1 * template.getForEntity(_ as String, DrugSearchResponse.class, _ as Object) >> resp

    }
}
