package com.alten.ecommerce.services;

import com.alten.ecommerce.dtos.AuthResponse;
import com.alten.ecommerce.dtos.LoginRequest;
import com.alten.ecommerce.dtos.UserDto;
import com.alten.ecommerce.entities.User;
import com.alten.ecommerce.mapper.UserMapper;
import com.alten.ecommerce.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {


    private final UserService userService;


    private final AuthenticationManager authenticationManager;


    private final JwtUtil jwtUtil;

    private final UserMapper userMapper;

    private final CustomUserDetailsService userDetailsService;

    public User createUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        return userService.saveUser(userMapper.userToUserDto(user));
    }


    public AuthResponse authenticateUser(LoginRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);

            return new AuthResponse(jwt);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
