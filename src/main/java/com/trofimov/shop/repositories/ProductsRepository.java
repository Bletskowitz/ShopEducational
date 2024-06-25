package com.trofimov.shop.repositories;

import com.trofimov.shop.entities.Category;
import com.trofimov.shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    Product findFirstById(Integer id);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByNameContainingIgnoreCase(String name);
}
