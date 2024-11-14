package com.challenge.ecommerce.modules.products.adapters.input.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequestDTO {
    private Long userId;
    private Long productId;
    private int amount;
}
