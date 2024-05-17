package dev.jeet.productservice.services;

import dev.jeet.productservice.dtos.FakeStoreProductDto;
import dev.jeet.productservice.models.Category;
import dev.jeet.productservice.models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreservice")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public Product getSingleProduct(Long id) {
//        FakeStoreProductDto fakeStoreProductDto =
//                restTemplate.getForObject(
//                        "https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        Product productFromCache = (Product) redisTemplate.opsForValue().get(String.valueOf(id));
        if(productFromCache != null) {
            return productFromCache;
        }

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(
                    "https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImageUrl());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        redisTemplate.opsForValue().set(String.valueOf(id), product);

        return product;
    }

    @Override
    public Product createProduct(String title, String description, double price, String imageUrl, String category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImageUrl(imageUrl);
        fakeStoreProductDto.setCategory(category);
        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products"
                                                    ,fakeStoreProductDto,FakeStoreProductDto.class);
            return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : response){
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;
    }

    @Override
    public Product deleteSingleProduct(Long id) {
        String url = "https://fakestoreapi.com/products/{id}";
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                url, HttpMethod.DELETE,null, FakeStoreProductDto.class,id);
            return responseEntity.getBody().toProduct();
    }

    @Override
    public Product updateProduct(Long id, String title, String description, double price, String imageUrl, String category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImageUrl(imageUrl);
        fakeStoreProductDto.setCategory(category);
        String url = "https://fakestoreapi.com/products/{id}";
        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<FakeStoreProductDto>(fakeStoreProductDto);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                url, HttpMethod.PUT, requestEntity, FakeStoreProductDto.class,id);
        return responseEntity.getBody().toProduct();
    }
}

