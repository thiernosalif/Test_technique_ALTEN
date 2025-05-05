package com.alten.ecommerce.dtos;

import lombok.Data;

@Data
public class AccountRequest {

    private String username;
    private String firstname;
    private String email;
    private String password;
}
