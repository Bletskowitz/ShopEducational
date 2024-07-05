package com.trofimov.shop.controllers;

import com.trofimov.shop.dtos.ProductDto;
import com.trofimov.shop.entities.Product;
import com.trofimov.shop.services.AuthService;
import com.trofimov.shop.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
@Tag(name = "Product controller", description = "Operations for products")
public class ProductController {

    private final ProductService service;

    @Operation(summary = "Get all products by category")
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<Product> getAllByCategory(@RequestParam(name = "category") String category) {
        return service.getAllByCategory(category);
    }

    @Operation(summary = "Get all products by name matching")
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAllByNameLike(@RequestParam(name = "productName") String name) {
        return service.getAllByNameLike(name);
    }

    @Operation(summary = "Add new product to DB")
    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('Product', 'CUSTOMER')")
    public ResponseEntity addNewProduct(@RequestBody ProductDto dto) {
        service.addNewProduct(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Delete product from DB")
    @RequestMapping(method = RequestMethod.DELETE)
    @PreAuthorize("hasPermission('Product', 'CUSTOMER')")
    public ResponseEntity deleteProduct(@RequestBody ProductDto dto) {
        service.deleteProduct(dto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
