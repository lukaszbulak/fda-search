package pl.square.fdasearch.store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.square.fdasearch.store.domain.Drug;

@Repository
public interface LocalDrugRepository extends CrudRepository<Drug, String> {

}
