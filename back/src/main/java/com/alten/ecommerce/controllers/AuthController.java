package com.alten.ecommerce.controllers;

import com.alten.ecommerce.dtos.JwtResponse;
import com.alten.ecommerce.dtos.LoginRequest;
import com.alten.ecommerce.entities.User;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.alten.ecommerce.utils.Constants.APP_API_ROOT;

@RestController

public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

   @PostMapping(value = APP_API_ROOT + "/token")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("Login endpoint hit: " + APP_API_ROOT + "/token");
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        //User user = (User) authentication.getPrincipal();
       org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
       String email = principal.getUsername();  // Ou autre méthode pour récupérer l'email
       User user = userRepository.findByEmail(email).orElseThrow(() ->
               new UsernameNotFoundException("User not found"));

       String jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of("token", jwt));
    }

   /* @PostMapping(value = APP_API_ROOT + "/token")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        // Vérifie l'utilisateur et génère le token JWT (code précédemment vu)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User userPrincipal = (User) authentication.getPrincipal();
        String jwt = jwtService.generateToken(userPrincipal);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }*/
}
