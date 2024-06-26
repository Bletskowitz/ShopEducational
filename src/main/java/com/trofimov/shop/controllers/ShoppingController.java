package com.trofimov.shop.controllers;

import com.trofimov.shop.dtos.PositionDto;
import com.trofimov.shop.services.ShopingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping")
@Tag(name = "Shopping controller", description = "Operations for users to manage products")
public class ShoppingController {
    private final ShopingService shopingService;

    @RequestMapping(method = RequestMethod.PUT)
    @Operation(summary = "Add product to user's order")
    public void addToProductToOrder(@RequestBody PositionDto dto) {
        shopingService.addProductToCurrentOrder(dto);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Operation(summary = "Finish current user's order")
    public void finishOrder(@RequestParam(name = "userId") Integer id) {
        shopingService.finishCurrentOrder(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Operation(summary = "Finish current user's order")
    public void deletePositionFromOrder(@RequestParam(name = "posId") Integer id) {
        shopingService.deletePosition(id);
    }
}
