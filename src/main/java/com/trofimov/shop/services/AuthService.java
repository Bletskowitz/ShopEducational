package com.trofimov.shop.services;

import com.trofimov.shop.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    public User getAuthenticatedUser() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    public Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
