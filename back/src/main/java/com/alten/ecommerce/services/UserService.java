package com.alten.ecommerce.services;

import com.alten.ecommerce.dtos.AccountRequest;
import com.alten.ecommerce.entities.User;
import com.alten.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public User getByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    public void registerUser(AccountRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstname(request.getFirstname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        repo.save(user);
    }
}
