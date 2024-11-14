package com.challenge.ecommerce.modules.users.adapters.input.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDTO {
    private String username;
    private String password;
    private String names;
    private String surnames;
    private String adress;
    private String email;
}
