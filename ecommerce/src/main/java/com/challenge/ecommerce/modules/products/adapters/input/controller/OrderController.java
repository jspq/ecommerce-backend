package com.challenge.ecommerce.modules.products.adapters.input.controller;

import com.challenge.ecommerce.modules.products.adapters.input.dto.order.AddProductRequestDTO;
import com.challenge.ecommerce.modules.products.adapters.input.dto.order.ConfirmOrderDTO;
import com.challenge.ecommerce.modules.products.adapters.input.dto.order.OrderProductSummaryDTO;
import com.challenge.ecommerce.modules.products.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public void createOrder(@RequestBody AddProductRequestDTO request) {
        orderService.addProductToShoppingCart(request.getUserId(), request.getProductId(), request.getAmount());
    }

    @PostMapping("/confirm/{id}")
    public void confirmOrder(@PathVariable Long id) {
        orderService.confirmOrder(id);
    }

    @GetMapping("/summary/{userId}")
    public ResponseEntity<List<OrderProductSummaryDTO>> getOrderSummary(@PathVariable Long userId) {
        List<OrderProductSummaryDTO> summary = orderService.getOrderProductSummaryByUser(userId);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<OrderProductSummaryDTO>> getOrderHistory(@PathVariable Long userId) {
        List<OrderProductSummaryDTO> summary = orderService.getOrderProductSummaryByUserId(userId);
        return ResponseEntity.ok(summary);
    }
}
