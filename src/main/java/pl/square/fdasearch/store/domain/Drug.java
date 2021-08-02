package pl.square.fdasearch.store.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Drug {
    @Id
    private String application_number;

    @ElementCollection
    List<String> manufacturer_name;
    @ElementCollection
    List<String> substance_name;
    @ElementCollection
    List<String> product_number;
}
