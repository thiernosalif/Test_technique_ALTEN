package com.alten.ecommerce.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public boolean isAdmin(Authentication authentication) {
        return authentication != null
                && "admin@admin.com".equals(authentication.getName());
    }
}
