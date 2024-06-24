package com.trofimov.shop.services;

import com.trofimov.shop.dtos.ProductDto;
import com.trofimov.shop.entities.Product;
import com.trofimov.shop.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductsRepository productsRepository;

    public List<Product> getAllByCategory(String category) {
        return productsRepository.findAllByCategory(category);
    }

    public List<Product> getAllByNameLike(String name) {
        return productsRepository.findAllByNameContainingIgnoreCase(name);
    }

    public void addNewProduct(ProductDto dto) {
        Product entity = new Product(dto.getName(), dto.getPrice(), dto.getCategory());
        productsRepository.save(entity);
    }
}
