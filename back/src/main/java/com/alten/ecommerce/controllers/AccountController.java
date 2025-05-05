package com.alten.ecommerce.controllers;

import com.alten.ecommerce.dtos.AccountRequest;
import com.alten.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alten.ecommerce.utils.Constants.APP_API_ROOT;

@RestController

public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping(value = APP_API_ROOT + "/account")
    public ResponseEntity<?> register(@RequestBody AccountRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok("Account registered successfully! !");
    }
}
