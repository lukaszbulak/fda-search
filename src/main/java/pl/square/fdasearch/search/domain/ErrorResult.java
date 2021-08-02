package pl.square.fdasearch.search.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorResult {

    String code;
    String message;
}
