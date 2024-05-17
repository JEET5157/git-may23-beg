package dev.jeet.productservice.dtos;

import dev.jeet.productservice.models.Category;
import dev.jeet.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductDto {
    private long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String category;

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.getId());
        product.setTitle(this.getTitle());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setImageUrl(this.getImageUrl());

        Category category = new Category();
        category.setTitle(this.getCategory());
        product.setCategory(category);
        return product;
    }
}
