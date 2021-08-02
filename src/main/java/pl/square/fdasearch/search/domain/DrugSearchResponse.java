package pl.square.fdasearch.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class DrugSearchResponse {

//    @JsonProperty
//    Map<String, String> meta;
    @JsonProperty
    List<DrugSearchResult> results;
}
