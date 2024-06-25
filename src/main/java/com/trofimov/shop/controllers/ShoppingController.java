package com.trofimov.shop.controllers;

import com.trofimov.shop.services.ShopingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping")
@Tag(name = "Shopping controller", description = "Operations for users to manage products")
public class ShoppingController {
    private ShopingService shopingService;

    @Autowired
    public ShoppingController(ShopingService shopingService) {
        this.shopingService = shopingService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Operation(summary = "Add product to user cart")
    public void addToUserCart() {

    }
}
