package com.trofimov.shop.configurations;

import com.trofimov.shop.services.UserDetailsService;
import com.trofimov.shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

        security.formLogin((lg) -> lg.loginPage("/login").defaultSuccessUrl("/"))
                .logout((lgt) -> lgt
                        .permitAll()
                        .logoutSuccessUrl("/"))
                .authorizeHttpRequests((authz) -> authz.requestMatchers("/products", "/products/**")
                .hasRole("ADMINISTRATOR")
                        .requestMatchers("/login").permitAll().anyRequest().authenticated());
        return security.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
