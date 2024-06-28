package com.trofimov.shop.controllers;

import com.trofimov.shop.dtos.UserDto;
import com.trofimov.shop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User controller", description = "Operations for creation users and log in")
public class UserController {
    private final UserService userService;

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
}
