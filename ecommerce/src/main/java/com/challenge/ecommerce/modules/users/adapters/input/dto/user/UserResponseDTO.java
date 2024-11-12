package com.challenge.ecommerce.modules.users.adapters.input.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private Set<String> roles;
}
