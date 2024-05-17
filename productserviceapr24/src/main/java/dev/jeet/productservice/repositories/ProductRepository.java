package dev.jeet.productservice.repositories;

import dev.jeet.productservice.models.Product;
import dev.jeet.productservice.repositories.projections.ProductWithTitleAndId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Product save(Product entity);

    Product findByIdIs(Long id);

    List<Product> findAll();


    void deleteById(Long id);

    @Query(value = "update Product p set p.price = :price where p.id = :id",nativeQuery = true)
    void updateByPrice(Long id, double price);

    @Query("select p from Product p where p.category.title = :title and p.id = :id")
    Product getProductWithASpecificTitleAndId(@Param("title") String title,@Param("id") Long id);

    @Query(value = "select p.id,p.title from product p where p.id = :id and p.category.title = :title",nativeQuery = true)
    ProductWithTitleAndId getProductWithASpecificTitleAndId2(@Param("title") String title, @Param("id") Long id);

    Page<Product> findByTitleContaining(String title,Pageable pageable);

}
