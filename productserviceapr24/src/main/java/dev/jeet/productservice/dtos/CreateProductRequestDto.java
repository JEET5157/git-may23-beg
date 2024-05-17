package dev.jeet.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {
    private long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String category;
}
