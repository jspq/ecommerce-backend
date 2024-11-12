package com.challenge.ecommerce.infraestructure.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
    //private String email;
    private String username;
    private String password;
}
