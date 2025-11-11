package com.ecommerce.product_service.infrastructure.dto;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String category,
        Instant createdAt
) { }
