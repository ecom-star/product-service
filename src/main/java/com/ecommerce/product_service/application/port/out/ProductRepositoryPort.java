package com.ecommerce.product_service.application.port.out;

import com.ecommerce.product_service.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    Product save(Product p);
    Optional<Product> findById(UUID id);
    boolean existsByName(String name);
    void deleteById(UUID id);
    Page<Product> findAll(Pageable pageable);
}
