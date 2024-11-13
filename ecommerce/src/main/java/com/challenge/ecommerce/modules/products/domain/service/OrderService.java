package com.challenge.ecommerce.modules.products.domain.service;

import com.challenge.ecommerce.modules.products.domain.model.Order;
import com.challenge.ecommerce.modules.products.domain.model.OrderProduct;
import com.challenge.ecommerce.modules.products.domain.repository.OrderProductRepository;
import com.challenge.ecommerce.modules.products.domain.repository.OrderRepository;
import com.challenge.ecommerce.modules.products.domain.repository.ProductRepository;
import com.challenge.ecommerce.modules.users.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public void addProductToShoppingCart(Long userId, Long productId, int amount) {
        Order order = orderRepository.findByUserIdAndState(userId, "PENDING")
                .orElseGet(() -> {
                    Order newOrder = new Order();
                    newOrder.setUser(userRepository.findById(userId).orElseThrow());
                    newOrder.setOrderDate(new Date());
                    newOrder.setState("PENDING");
                    return orderRepository.save(newOrder);
                });

        Optional<OrderProduct> optionalOrderProduct = orderProductRepository.findByOrderIdAndProductId(
                order.getId(), productId
        );

        if (optionalOrderProduct.isPresent()) {
            OrderProduct orderProduct = optionalOrderProduct.get();
            orderProduct.setAmount(amount);
            orderProductRepository.save(orderProduct);
        } else {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(productRepository.findById(productId).orElseThrow());
            orderProduct.setAmount(amount);
            orderProductRepository.save(orderProduct);
        }
    }

    public void confirmOrder(Long userId) {
        Order order = orderRepository.findByUserIdAndState(userId, "PENDING")
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el carro de compras"));
        order.setState("CONFIRMED");
        order.setConfirmDate(new Date());
        orderRepository.save(order);
    }

}
