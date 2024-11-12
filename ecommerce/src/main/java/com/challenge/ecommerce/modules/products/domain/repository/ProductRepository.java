package com.challenge.ecommerce.modules.products.domain.repository;

import com.challenge.ecommerce.modules.products.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
