package com.alten.ecommerce.dtos;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
