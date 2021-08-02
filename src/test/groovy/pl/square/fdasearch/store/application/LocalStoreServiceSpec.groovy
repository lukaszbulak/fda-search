package pl.square.fdasearch.store.application

import pl.square.fdasearch.search.domain.DrugSearchResult
import pl.square.fdasearch.search.domain.OpenFda
import pl.square.fdasearch.search.domain.Product
import pl.square.fdasearch.store.domain.Drug
import pl.square.fdasearch.store.repository.LocalDrugRepository
import spock.lang.Specification

class LocalStoreServiceSpec extends Specification {

    def repository = Mock(LocalDrugRepository)
    def service = new LocalStoreService(repository)

    def "storeLocally when no entry exist"() {

        given:
        def searchResult = new DrugSearchResult(application_number: "A1234",
                openfda: new OpenFda(manufacturer_name: ["drugManufacturer"] as List),
                products: [new Product(product_number: "prod1234")] as List)

        when:
        def result = service.storeLocally(searchResult, "A1234")

        then:
        result == "created"
        1 * repository.findById("A1234") >> Optional.empty()
        1 * repository.save({
            ((Drug)it).application_number == "A1234" })
    }
}
