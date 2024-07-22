package com.trofimov.shop;

import com.trofimov.shop.controllers.ProductController;
import com.trofimov.shop.dtos.CategoryDto;
import com.trofimov.shop.dtos.ProductDto;
import com.trofimov.shop.entities.Product;
import com.trofimov.shop.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;

@SpringBootTest
class ShopApplicationTests {
    @Autowired
    private ProductController productController;
    @Autowired
    private ProductService productService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
    }

    @Test
    @WithUserDetails(value = "customer", userDetailsServiceBeanName = "customUserDetailsService")
    void testCustomerAuth() {
        ProductDto dto = new ProductDto();
        dto.setName("customName");
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("meat");
        dto.setCategory(categoryDto);
        productController.addNewProduct(dto);
        Product product = productService.getFirstByNameFull(dto.getName());
        dto.setId(product.getId());
        productController.deleteProduct(dto);
    }

    @Test
    @WithUserDetails(value = "user", userDetailsServiceBeanName = "customUserDetailsService")
    void testAdminAuth() {
        ProductDto dto = new ProductDto();
        dto.setName("customName");
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("meat");
        dto.setCategory(categoryDto);
        productController.addNewProduct(dto);
        Product product = productService.getFirstByNameFull(dto.getName());
        dto.setId(product.getId());
        productController.deleteProduct(dto);
    }

}
