package com.ecommerce.product_service.application.service;

import com.ecommerce.product_service.application.port.in.ProductUseCase;
import com.ecommerce.product_service.application.port.out.ProductRepositoryPort;
import com.ecommerce.product_service.domain.model.Product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductService implements ProductUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product create(Product product) {
        if (product.getPrice() != null && product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (productRepositoryPort.existsByName(product.getName())) {
            throw new IllegalArgumentException("Product with the same name already exists");
        }
        product.setId(UUID.randomUUID());
        product.setCreatedAt(Instant.now());
        return productRepositoryPort.save(product);
    }

    @Override
    public Product update(UUID id, Product product) {
        Product existing = productRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (Objects.nonNull(product.getName()))
            existing.setName(product.getName());
        if (Objects.nonNull(product.getDescription()))
            existing.setDescription(product.getDescription());
        if (Objects.nonNull(product.getPrice()))
            existing.setPrice(product.getPrice());
        if (Objects.nonNull(product.getStock()))
            existing.setStock(product.getStock());
        if (Objects.nonNull(product.getCategory()))
            existing.setCategory(product.getCategory());

        return productRepositoryPort.save(existing);
    }

    @Override
    public Product getById(UUID id) {
        return productRepositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public void delete(UUID id) {
        // ensure it exists
        productRepositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        productRepositoryPort.deleteById(id);
    }

    @Override
    public Page<Product> list(Pageable pageable) {
        return productRepositoryPort.findAll(pageable);
    }
}
