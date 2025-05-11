package com.alten.ecommerce.security;

import com.alten.ecommerce.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Component("securityConfig")
public class SecurityConfig {

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/swagger-resources/**"
    };


    private final JwtUtil jwtUtil;


    private final CustomUserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/api/v1/ecom/account", "/api/v1/ecom/token", "/h2-console/**").permitAll()
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/ecom/products/**").permitAll()

                        // Utilisation directe des rôles Spring Security
                        .requestMatchers(HttpMethod.POST, "/api/v1/ecom/products").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/ecom/products/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/ecom/products/**").hasAuthority("ROLE_ADMIN")

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtAuthFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        WebExpressionAuthorizationManager adminAccessManager = new WebExpressionAuthorizationManager("@securityConfig.isAdmin(authentication)");

        http
                .cors(Customizer.withDefaults()) // CORS par défaut
                .csrf(csrf -> csrf.disable()) // Désactive CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permet toutes les OPTIONS requests
                        .requestMatchers("/error").permitAll() // Permet l'accès à l'endpoint /error
                        .requestMatchers("/api/v1/ecom/account", "/api/v1/ecom/token", "/h2-console/**").permitAll() // Permet l'accès à ces endpoints sans authentification
                        .requestMatchers(WHITE_LIST_URL).permitAll() // Permet l'accès aux URLs de la whitelist
                        .requestMatchers(HttpMethod.GET, "/api/v1/ecom/products/**").permitAll() // Permet l'accès aux produits en GET

                        // Protection des actions d'ajout, modification et suppression des produits pour l'admin
                        .requestMatchers(HttpMethod.POST, "/api/v1/ecom/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/ecom/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/ecom/products/**").hasRole("ADMIN")
                       *//* .requestMatchers(HttpMethod.POST, "/api/v1/ecom/products").access(adminAccessManager)
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/ecom/products/**").access(adminAccessManager)
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/ecom/products/**").access(adminAccessManager)*//*

                        .anyRequest().authenticated() // Toutes les autres requêtes doivent être authentifiées
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Utilisation de la gestion d'état sans session
                .addFilterBefore(new JwtAuthFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class); // Ajout du filtre JWT

        return http.build();
    }*/



    // Méthode vérifiant si l'utilisateur est un administrateur (en se basant sur son email)
    public boolean isAdmin(Authentication authentication) {
        String email = authentication.getName(); // Récupération de l'email de l'utilisateur connecté
        return "admin@admin.com".equalsIgnoreCase(email); // Vérifie si l'email correspond
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encodage des mots de passe avec BCrypt
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // Configuration du gestionnaire d'authentification
    }
}