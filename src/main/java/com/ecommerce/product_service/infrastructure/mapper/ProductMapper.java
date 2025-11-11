package com.ecommerce.product_service.infrastructure.mapper;

import com.ecommerce.product_service.domain.model.Product;
import com.ecommerce.product_service.infrastructure.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toDomain(ProductEntity e) {
        Product product = new Product();
        product.setId(e.getId());
        product.setName(e.getName());
        product.setDescription(e.getDescription());
        product.setPrice(e.getPrice());
        product.setStock(e.getStock());
        product.setCategory(e.getCategory());
        product.setCreatedAt(e.getCreatedAt());

        return product;
    }

    public ProductEntity toEntity(Product p) {
        ProductEntity e = new ProductEntity();
        e.setId(p.getId());
        e.setName(p.getName());
        e.setDescription(p.getDescription());
        e.setPrice(p.getPrice());
        e.setStock(p.getStock());
        e.setCategory(p.getCategory());
        e.setCreatedAt(p.getCreatedAt());
        return e;
    }
}
