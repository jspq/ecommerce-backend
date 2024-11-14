package com.challenge.ecommerce.modules.products.domain.repository;

import com.challenge.ecommerce.modules.products.domain.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
