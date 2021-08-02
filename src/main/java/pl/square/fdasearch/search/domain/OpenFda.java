package pl.square.fdasearch.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class OpenFda {
    List<String> application_number;
//    @JsonProperty
//    List<String>  brand_name;
    @JsonProperty
    List<String> manufacturer_name;
    @JsonProperty
    List<String> substance_name;
}
