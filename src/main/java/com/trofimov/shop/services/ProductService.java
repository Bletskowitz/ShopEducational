package com.trofimov.shop.services;

import com.trofimov.shop.dtos.ProductDto;
import com.trofimov.shop.entities.Category;
import com.trofimov.shop.entities.Product;
import com.trofimov.shop.repositories.CategoryRepository;
import com.trofimov.shop.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getAllByCategory(String categoryName) {
        return productsRepository.findAllByCategoryName(categoryName);
    }

    public List<Product> getAllByNameLike(String name) {
        return productsRepository.findAllByNameContainingIgnoreCase(name);
    }

    public void addNewProduct(ProductDto dto) {
        Category category = categoryRepository.findFirstByNameIgnoreCase(dto.getCategory().getName());
        Product entity = new Product(dto.getName(), dto.getPrice(), category);
        productsRepository.save(entity);
    }
}
