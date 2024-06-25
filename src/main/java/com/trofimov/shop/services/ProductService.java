package com.trofimov.shop.services;

import com.trofimov.shop.dtos.ProductDto;
import com.trofimov.shop.entities.Category;
import com.trofimov.shop.entities.Product;
import com.trofimov.shop.repositories.CategoryRepository;
import com.trofimov.shop.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductsRepository productsRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public ProductService(ProductsRepository productsRepository, CategoryRepository categoryRepository) {
        this.productsRepository = productsRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllByCategory(String category) {
        return productsRepository.findAllByCategory(category);
    }

    public List<Product> getAllByNameLike(String name) {
        return productsRepository.findAllByNameContainingIgnoreCase(name);
    }

    public void addNewProduct(ProductDto dto) {
        Category category = categoryRepository.findFirstByNameContainingIgnoreCase(dto.getCategory().getName());
        Product entity = new Product(dto.getName(), dto.getPrice(), category);
        productsRepository.save(entity);
    }
}
