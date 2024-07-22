package com.trofimov.shop.configurations.userdetails;

import com.trofimov.shop.entities.User;
import com.trofimov.shop.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.atInfo().log("CORRECT LOAD USER");
        User user = userService.findUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("user with username '" + username + "' not found");
        }
        return CustomUserDetails.detailsFromUser(user);
    }
}
