package dev.jeet.productservice.controllers;

import dev.jeet.productservice.dtos.SearchRequestDto;
import dev.jeet.productservice.models.Product;
import dev.jeet.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public Page<Product> search(@RequestBody SearchRequestDto searchRequestDto){
        return searchService.search(searchRequestDto.getQuery(), searchRequestDto.getPageNo(),
                                    searchRequestDto.getPageSize());
    }
}
