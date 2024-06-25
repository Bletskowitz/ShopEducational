package com.trofimov.shop.controllers;

import com.trofimov.shop.dtos.ProductDto;
import com.trofimov.shop.entities.Product;
import com.trofimov.shop.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product controller", description = "Operations for products")
public class ProductController {

    private ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(summary = "Get all products by category")
    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
    public List<Product> getAllByCategory(@PathVariable String category) {
        return service.getAllByCategory(category);
    }

    @Operation(summary = "Get all products by name matching")
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public List<Product> getAllByNameLike(@PathVariable String name) {
        return service.getAllByNameLike(name);
    }

    @Operation(summary = "Add new product to DB")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addNewProduct(@RequestBody ProductDto dto) {
        service.addNewProduct(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Delete product from DB")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@RequestBody ProductDto dto) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
