package pl.square.fdasearch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.List;

@ToString
public class DrugSearchResponse {

//    @JsonProperty
//    Map<String, String> meta;
    @JsonProperty
    List<DrugSearchResult> results;
}
