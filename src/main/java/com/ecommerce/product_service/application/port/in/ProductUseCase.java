package com.ecommerce.product_service.application.port.in;

import com.ecommerce.product_service.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductUseCase {
    Product create(Product p);
    Product update(UUID id, Product p);
    Product getById(UUID id);
    void delete(UUID id);
    Page<Product> list(Pageable pageable);
}
