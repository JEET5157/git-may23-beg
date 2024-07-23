package dev.jeet.productservice.services;

import dev.jeet.productservice.models.Product;
import dev.jeet.productservice.repositories.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private ProductRepository productRepository;
    public SearchService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public Page<Product> search(String query, int pageNo, int pageSize){
        Sort sort = Sort.by("title").descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return productRepository.findByTitleContaining(query,pageable);
    }
}
