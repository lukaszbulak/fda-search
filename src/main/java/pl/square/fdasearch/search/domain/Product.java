package pl.square.fdasearch.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@ToString
//@Data
public class Product {
    @JsonProperty
    String product_number;

    public String getProduct_number() {
        return product_number;
    }
}
