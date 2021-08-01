package pl.square.fdasearch.application

import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import pl.square.fdasearch.domain.DrugSearchResponse
import spock.lang.Specification

import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.when

@SpringBootTest
@EnableAutoConfiguration(exclude= DataSourceAutoConfiguration)
class SearchServiceSpec extends Specification {

    @Mock
    RestTemplate template;

    @Autowired
    SearchService service;

    def "SearchForBrand"() {

        given:
        ResponseEntity<DrugSearchResponse> resp = Mock(ResponseEntity<DrugSearchResponse>) {
            getBody() >> "some body"
        }
        when(template.getForEntity(anyString(), eq(DrugSearchResponse))).thenReturn(resp)

        when:
        def result = service.searchForBrand("apap");

        then:
        !result.isBlank()
        // 1 * template.getForEntity(_ as String, DrugSearchResponse) >> resp
        println result

    }
}
