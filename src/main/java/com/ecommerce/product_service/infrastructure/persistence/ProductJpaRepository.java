package com.ecommerce.product_service.infrastructure.persistence;

import com.ecommerce.product_service.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
    boolean existsByName(String name);
}
