package com.challenge.ecommerce.modules.products.adapters.input.controller;

import com.challenge.ecommerce.modules.products.adapters.input.dto.order.AddProductRequestDTO;
import com.challenge.ecommerce.modules.products.adapters.input.dto.order.ConfirmOrderDTO;
import com.challenge.ecommerce.modules.products.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public void createOrder(@RequestBody AddProductRequestDTO request) {
        orderService.addProductToShoppingCart(request.getUserId(), request.getProductId(), request.getAmount());
    }

    @PostMapping("/confirm")
    public void confirmOrder(@RequestBody ConfirmOrderDTO request) {
        orderService.confirmOrder(request.getUserId());
    }
}
