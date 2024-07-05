package com.trofimov.shop;

import com.trofimov.shop.controllers.ProductController;
import com.trofimov.shop.dtos.CategoryDto;
import com.trofimov.shop.dtos.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class ShopApplicationTests {
    @Autowired
    private ProductController productController;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
    }

    @Test
    @WithMockUser(username = "customer", password = "12345")
    void testCustomerAuth() {
        ProductDto dto = new ProductDto();
        dto.setName("customName");
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("meat");
        dto.setCategory(categoryDto);
        productController.addNewProduct(dto);
        productController.deleteProduct(dto);
    }

    @Test
    @WithMockUser(username = "user", password = "123")
    void testAdminAuth() {
        ProductDto dto = new ProductDto();
        dto.setName("customName");
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("meat");
        dto.setCategory(categoryDto);
        productController.addNewProduct(dto);
        productController.deleteProduct(dto);
    }

}
