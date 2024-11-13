package com.challenge.ecommerce.modules.products.domain.repository;

import com.challenge.ecommerce.modules.products.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUserIdAndState(Long userId, String state);
}