package dev.jeet.productservice.repositories;

import dev.jeet.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);

    Category save(Category category);

    Optional<Category> findById(Long Id);
}
