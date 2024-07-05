package com.trofimov.shop.configurations;

import com.trofimov.shop.entities.Role;
import com.trofimov.shop.entities.User;
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

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        User user = (User) authentication.getPrincipal();
        for(Role role: user.getRoles()) {
            log.atInfo().log(role.getAuthority());
        }
        return user.getRoles().stream().anyMatch((role) -> role.getName().toString().equals(permission));
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
