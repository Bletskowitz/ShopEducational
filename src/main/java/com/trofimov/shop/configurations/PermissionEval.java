package com.trofimov.shop.configurations;

import com.trofimov.shop.enums.UserRole;
import com.trofimov.shop.repositories.OrdersRepository;
import com.trofimov.shop.repositories.RoleRepository;
import com.trofimov.shop.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("permissionEval")
@Slf4j
@RequiredArgsConstructor
public class PermissionEval implements PermissionEvaluator {
    private final AuthService authService;
    private final RoleRepository roleRepository;
    private final OrdersRepository ordersRepository;

    public boolean hasAdminRole() {
        return authService.getAuthenticatedUser().getRoles().contains(roleRepository.findFirstByName(UserRole.ADMINISTRATOR));
    }

    public boolean hasPermissionToModifyOrder(Integer orderId) {
        log.atInfo().log("Checking permission for user " + authService.getAuthenticatedUser().getUsername() + " to modify order " + orderId);
        return ordersRepository.findFirstById(orderId).getUser().getId().equals(authService.getAuthenticatedUser().getId());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
