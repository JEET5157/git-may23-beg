package dev.jeet.productservice.services;

import dev.jeet.productservice.models.Category;
import dev.jeet.productservice.models.Product;
import dev.jeet.productservice.repositories.CategoryRepository;
import dev.jeet.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfproductservice")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) {
        return productRepository.findByIdIs(id);
    }

    @Override
    public Product createProduct(String title, String description, double price, String imageUrl, String categoryTitle) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        Category categoryFromDatabase = categoryRepository.findByTitle(categoryTitle);
        if(categoryFromDatabase == null){
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            categoryFromDatabase = categoryRepository.save(newCategory);
        }
        product.setCategory(categoryFromDatabase);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product deleteSingleProduct(Long id) {
        Optional<Product> productFromDb = productRepository.findById(id);
        if (productFromDb.isPresent()) {
            productRepository.deleteById(id);
            return productFromDb.get();
        }
        return null;
    }
    @Override
    public Product updateProduct(Long id, String title, String description, double price, String imageUrl, String categoryTitle) {
        Product updatedProduct = productRepository.findByIdIs(id);
        updatedProduct.setPrice(price);
        updatedProduct.setTitle(title);
        updatedProduct.setDescription(description);
        updatedProduct.setImageUrl(imageUrl);
        Category categoryFromDatabase = categoryRepository.findByTitle(categoryTitle);
        if(categoryFromDatabase == null){
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            categoryFromDatabase = categoryRepository.save(newCategory);
        }
        updatedProduct.setCategory(categoryFromDatabase);
        return productRepository.save(updatedProduct);
    }

}
