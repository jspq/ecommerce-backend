package com.challenge.ecommerce.modules.products.adapters.input.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String name;
    private String description;
    private Double price;
    private Long catalogId;
    private Boolean isActive;
}
