package com.trofimov.shop.services;

import com.trofimov.shop.dtos.ProductDto;
import com.trofimov.shop.dtos.UserDto;
import com.trofimov.shop.entities.Product;
import com.trofimov.shop.entities.User;
import com.trofimov.shop.repositories.ProductsRepository;
import com.trofimov.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopingService {
    private UserRepository userRepository;
    private ProductsRepository productsRepository;

    @Autowired
    public ShopingService(UserRepository userRepository, ProductsRepository productsRepository) {
        this.userRepository = userRepository;
        this.productsRepository = productsRepository;
    }

    public void addProductToCart(UserDto userDto, ProductDto productDto) {
        User user = userRepository.findFirstById(userDto.getId());
        Product product = productsRepository.findFirstById(productDto.getId());
        user.getCart().add(product);
        userRepository.save(user);
    }

    public List<Product> getUserCart(UserDto dto) {
        return userRepository.findFirstById(dto.getId()).getCart();
    }
}
