package dev.jeet.productservice;

import dev.jeet.productservice.models.Category;
import dev.jeet.productservice.models.Product;
import dev.jeet.productservice.repositories.CategoryRepository;
import dev.jeet.productservice.repositories.ProductRepository;
import dev.jeet.productservice.repositories.projections.ProductWithTitleAndId;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class Productserviceapr24ApplicationTests {
//    @Autowired
//    ProductRepository productRepository;
//
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void testingQuery(){
//        Product product = productRepository.getProductWithASpecificTitleAndId("phones", 2L);
//        System.out.println(product.getTitle());
//    }
//
//    @Test
//    public void testingQuery2(){
//        ProductWithTitleAndId product = productRepository.getProductWithASpecificTitleAndId2("phones", 2L);
//        System.out.println(product.getTitle());
//        System.out.println(product.getId());
//    }
//
//    @Test
//    @Transactional
//    public void testingFetchTypes(){
//        Category category = categoryRepository.findByTitle("Phones");
//        System.out.println(category.getProducts());
//    }
//
//    @Test
//    public void testingFetchTypes2(){
//        Category category = categoryRepository.findByTitle("Phones");
//        System.out.println(category.getTitle());
//    }
//
//    @Test
//    public void testingFetchTypes3(){
//        Optional<Category> category = categoryRepository.findById(2L);
//        System.out.println(category.get().getTitle());
//    }
}
