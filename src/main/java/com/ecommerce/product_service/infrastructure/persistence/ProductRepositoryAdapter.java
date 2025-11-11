package com.ecommerce.product_service.infrastructure.persistence;

import com.ecommerce.product_service.application.port.out.ProductRepositoryPort;
import com.ecommerce.product_service.domain.model.Product;
import com.ecommerce.product_service.infrastructure.mapper.ProductMapper;
import com.ecommerce.product_service.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {
    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    public ProductRepositoryAdapter(ProductJpaRepository productJpaRepository, ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product p) {
        ProductEntity productEntity = productJpaRepository.save(productMapper.toEntity(p));
        return productMapper.toDomain(productEntity);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productJpaRepository.findById(id).map(productMapper::toDomain);
    }

    @Override
    public boolean existsByName(String name) {
        return productJpaRepository.existsByName(name);
    }

    @Override
    public void deleteById(UUID id) {
        productJpaRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productJpaRepository.findAll(pageable).map(productMapper::toDomain);
    }
}
