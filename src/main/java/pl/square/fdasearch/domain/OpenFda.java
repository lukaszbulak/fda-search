package pl.square.fdasearch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.List;

@ToString
public class OpenFda {
    List<String> application_number;
    String brand_name;
    @JsonProperty
    List<String> manufacturer_name;
    @JsonProperty
    List<String> substance_name;
}
