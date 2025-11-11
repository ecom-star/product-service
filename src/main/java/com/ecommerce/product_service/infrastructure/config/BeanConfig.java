package com.ecommerce.product_service.infrastructure.config;

import com.ecommerce.product_service.application.port.in.ProductUseCase;
import com.ecommerce.product_service.application.port.out.ProductRepositoryPort;
import com.ecommerce.product_service.application.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ProductUseCase productUseCase(ProductRepositoryPort productRepositoryPort) {
        return new ProductService(productRepositoryPort);
    }
}
