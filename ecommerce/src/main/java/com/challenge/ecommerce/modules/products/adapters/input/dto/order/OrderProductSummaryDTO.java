package com.challenge.ecommerce.modules.products.adapters.input.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductSummaryDTO {

    private String productName;
    private int amount;
    private double price;

    // Constructor
    public OrderProductSummaryDTO(String productName, int amount, double price) {
        this.productName = productName;
        this.amount = amount;
        this.price = price;
    }

}
