package pl.square.fdasearch.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class DrugSearchResult {
    // List<Submission> submissions;
    @JsonProperty
    String application_number;
    String sponsor_name;
    @JsonProperty
    OpenFda openfda;
    @JsonProperty
    List<Product> products;
}
