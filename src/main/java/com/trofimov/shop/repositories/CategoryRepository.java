package com.trofimov.shop.repositories;

import com.trofimov.shop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findFirstById(Integer id);
    Category findFirstByNameContainingIgnoreCase(String name);
    List<Category> findAllByIdIsNotNull();
}
