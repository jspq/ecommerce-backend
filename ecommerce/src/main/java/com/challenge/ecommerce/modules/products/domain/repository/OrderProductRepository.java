package com.challenge.ecommerce.modules.products.domain.repository;

import com.challenge.ecommerce.modules.products.domain.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    Optional<OrderProduct> findByOrderIdAndProductId(Long orderId, Long productId);
}
