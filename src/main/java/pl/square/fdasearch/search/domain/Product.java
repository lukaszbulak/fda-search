package pl.square.fdasearch.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class Product {
    @JsonProperty
    String product_number;
}
