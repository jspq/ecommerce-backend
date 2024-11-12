package com.challenge.ecommerce.modules.users.adapters.input.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponseDTO {
    private String username;
    private String names;
    private String surnames;
    private String adress;
    private String email;
    /*private List<Order> orders;*/
    private Long numberOfOrders;
}
