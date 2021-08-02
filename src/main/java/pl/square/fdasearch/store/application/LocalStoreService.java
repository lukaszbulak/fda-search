package pl.square.fdasearch.store.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.square.fdasearch.search.domain.DrugSearchResult;
import pl.square.fdasearch.search.domain.Product;
import pl.square.fdasearch.store.domain.Drug;
import pl.square.fdasearch.store.repository.LocalDrugRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocalStoreService {

    private final LocalDrugRepository repository;

    public String storeLocally(DrugSearchResult result, String application_number) {
        // check already in db
        var dbEntity = repository.findById(application_number);
        Drug entity;
        final String modeInfo;
        if (dbEntity.isPresent()) {
            modeInfo = "updated";
            entity = dbEntity.get();
        } else {
            modeInfo = "created";
            entity = new Drug();
            entity.setApplication_number(application_number);
        }

        entity.setManufacturer_name(result.getOpenfda().getManufacturer_name());

        List<String> productNumbers = result.getProducts().stream()
                .map(Product::getProduct_number).collect(Collectors.toList());
        entity.setProduct_number(productNumbers);
        entity.setSubstance_name(result.getOpenfda().getSubstance_name());

        // create or update
        repository.save(entity);

        return modeInfo;
    }
}
