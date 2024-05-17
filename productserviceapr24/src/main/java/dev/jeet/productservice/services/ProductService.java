package dev.jeet.productservice.services;

import dev.jeet.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(Long id);
    public Product createProduct(String title, String description, double price, String imageUrl,
                                 String category);
    public List<Product> getAllProducts();
    public Product deleteSingleProduct(Long id);
    public Product updateProduct(Long id,String title, String description, double price, String imageUrl,
                              String category);
}
