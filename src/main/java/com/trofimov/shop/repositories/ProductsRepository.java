package com.trofimov.shop.repositories;

import com.trofimov.shop.entities.Category;
import com.trofimov.shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    Product findFirstById(Integer id);
    List<Product> findAllByCategory(Category category);
    @Query(value = "SELECT pr FROM Product pr WHERE pr.category.name = :category")
    List<Product> findAllByCategoryName(@Param("category") String category);
    List<Product> findAllByNameContainingIgnoreCase(String name);

    Product findFirstByName(String name);

    void deleteById(Integer id);
}
