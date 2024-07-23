package dev.jeet.productservice.controllers;

import dev.jeet.productservice.exceptions.ProductNotFoundException;
import dev.jeet.productservice.models.Product;
import dev.jeet.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    @Qualifier("fakestoreservice")
    private ProductService productService;

    @Test
    public void testGetAllProductsReturningListOfProducts() {
        Product p1 = new Product();
        p1.setTitle("Product1");
        p1.setId(1L);
        p1.setDescription("Awesome product 1");

        Product p2 = new Product();
        p2.setTitle("Product2");
        p2.setId(2L);
        p2.setDescription("Awesome product 2");

        List<Product> dummyProducts = new ArrayList<>();
        dummyProducts.add(p1);
        dummyProducts.add(p2);

        when(productService.getAllProducts()).thenReturn(dummyProducts);


        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

        List<Product> products = responseEntity.getBody();

        assertEquals(2, products.size());
        assertEquals("Product1", products.get(0).getTitle());
        assertEquals("Awesome product 1", products.get(0).getDescription());
    }

    @Test
    public void testGetAllProductsExceptionThrownWhenCalled() {
        when(productService.getAllProducts()).thenThrow(ProductNotFoundException.class);


        assertThrows(ProductNotFoundException.class, () -> productController.getAllProducts());
    }

    @Test
    public void testProductsGetByIdReturningProduct() {
        Product p1 = new Product();
        long id = 1;
        p1.setTitle("Product1");
        p1.setId(id);
        p1.setDescription("Awesome product 1");

        when(productService.getSingleProduct(id)).thenReturn(p1);

        Product realpro = productController.getProductById(id);

        assertEquals("Product1", realpro.getTitle());

    }

}