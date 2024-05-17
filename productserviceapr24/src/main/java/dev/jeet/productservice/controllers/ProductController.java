package dev.jeet.productservice.controllers;

import dev.jeet.productservice.dtos.CreateProductRequestDto;
import dev.jeet.productservice.dtos.ErrorDto;
import dev.jeet.productservice.models.Product;
import dev.jeet.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier("selfproductservice") ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto productRequestDto){
        return productService.createProduct(productRequestDto.getTitle(),
                                        productRequestDto.getDescription(),
                                        productRequestDto.getPrice(),
                                        productRequestDto.getImageUrl(),
                                        productRequestDto.getCategory());
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> responseData= productService.getAllProducts();
        ResponseEntity<List<Product>> responseEntity =
                new ResponseEntity<>(responseData, HttpStatusCode.valueOf(202));
        return responseEntity;
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getSingleProduct(id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteSingleProduct(@PathVariable("id") Long id){
        productService.deleteSingleProduct(id);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody CreateProductRequestDto productRequestDto){
        return productService.updateProduct(id,
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl(),
                productRequestDto.getCategory());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullPointerException(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Something went wrong. Please try again");
        return new ResponseEntity<>(errorDto,HttpStatusCode.valueOf(404));
    }
}
