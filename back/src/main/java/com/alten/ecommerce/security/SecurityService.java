package com.alten.ecommerce.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component("serviceSecurity")
public class SecurityService {

   /* public boolean isAdmin(Authentication authentication) {
        return authentication != null
                && "admin@admin.com".equals(authentication.getName());
    }*/

    public boolean isAdmin(Authentication authentication) {
        return authentication != null &&
                authentication.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}
