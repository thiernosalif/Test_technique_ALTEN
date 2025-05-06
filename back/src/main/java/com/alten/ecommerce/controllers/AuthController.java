package com.alten.ecommerce.controllers;

import com.alten.ecommerce.dtos.AuthResponse;
import com.alten.ecommerce.dtos.JwtResponse;
import com.alten.ecommerce.dtos.LoginRequest;
import com.alten.ecommerce.dtos.UserDto;
import com.alten.ecommerce.entities.User;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.security.JwtUtil;
import com.alten.ecommerce.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import static com.alten.ecommerce.utils.Constants.APP_API_ROOT;

@RestController

public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(APP_API_ROOT + "/account")
    public ResponseEntity<?> createAccount(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createUser(userDto));
    }


   @PostMapping(value = APP_API_ROOT + "/token")
   public Map<String, String> login(@RequestBody LoginRequest request) {

       String email = request.getEmail();
       String password = request.getPassword();

       User user = userRepository.findByEmail(email)
               .orElseThrow(() -> new RuntimeException("Email  incorrect"));

       System.out.println("Password in DB: " + user.getPassword());
       System.out.println("Password entered: " + password);
       System.out.println("Password in DB: " + user.getEmail());
       System.out.println("Password entered: " + email);

       if (!passwordEncoder.matches(password, user.getPassword())) {
           throw new RuntimeException("mot de passe incorrect");
       }


       String token = jwtUtil.generateToken(email);
       return Map.of("token", token);
   }

}
