package pl.square.fdasearch.search.application


import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import pl.square.fdasearch.search.domain.DrugSearchResponse
import pl.square.fdasearch.search.domain.DrugSearchResult
import spock.lang.Specification

class SearchServiceSpec extends Specification {

    RestTemplate template = Mock(RestTemplate);

    SearchService service = new SearchService(template);

    def "SearchForBrand"() {

        given:
        ResponseEntity<DrugSearchResponse> resp = Mock(ResponseEntity<DrugSearchResponse>) {
            getBody() >> new DrugSearchResponse(results: [new DrugSearchResult(application_number: "1234")] as List)
        }

        when:
        def result = service.searchForBrand("apap");

        then:
        !result.isBlank()
         1 * template.getForEntity(_ as String, DrugSearchResponse, _) >> resp
        println result

    }
}