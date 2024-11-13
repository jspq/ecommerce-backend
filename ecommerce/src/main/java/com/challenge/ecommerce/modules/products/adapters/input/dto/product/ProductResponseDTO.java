package com.challenge.ecommerce.modules.products.adapters.input.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long catalogId;
    private Boolean isActive;
}