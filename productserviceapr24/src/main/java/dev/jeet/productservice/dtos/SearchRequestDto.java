package dev.jeet.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    private String query;
    private int pageNo;
    private int pageSize;
}
