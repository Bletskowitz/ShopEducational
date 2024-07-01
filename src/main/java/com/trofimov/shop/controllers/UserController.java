package com.trofimov.shop.controllers;

import com.trofimov.shop.dtos.OrderDto;
import com.trofimov.shop.dtos.UserDto;
import com.trofimov.shop.services.AuthService;
import com.trofimov.shop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User controller", description = "Operations for creation users and log in")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Operation(summary = "create new user")
    public UserDto createNewUser(@RequestBody UserDto dto) {
        userService.createNewUser(dto);
        return dto;
    }

    @RequestMapping(value = "/promote", method = RequestMethod.POST)
    @Operation(summary = "promote user to admin")
    public UserDto promoteUser(@RequestBody UserDto dto) {
        userService.grantUserAuthorities(dto);
        return dto;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @Operation(summary = "promote user to admin")
    public List<OrderDto> getAllUsersOrders() {
        return userService.getAllOrders(authService.getAuthenticatedUser());
    }
}
