package com.alten.ecommerce.services;

import com.alten.ecommerce.entities.User;
import com.alten.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       /* User user = repository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));

        String role = "USER";
        if ("admin@admin.com".equalsIgnoreCase(user.getEmail())) {
            role = "ADMIN";
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );*/

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(
                        "admin@admin.com".equals(email) ? "ROLE_ADMIN" : "ROLE_USER"
                ))
        );
    }
}
