package com.challenge.ecommerce.modules.users.adapters.input.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserRequestDTO {
    private String username;
    private String password;
    private String email;
    private Set<Long> roles;
}
