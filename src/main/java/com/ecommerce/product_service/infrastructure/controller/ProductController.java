package com.ecommerce.product_service.infrastructure.controller;

import com.ecommerce.product_service.application.port.in.ProductUseCase;
import com.ecommerce.product_service.domain.model.Product;
import com.ecommerce.product_service.infrastructure.dto.PageResponse;
import com.ecommerce.product_service.infrastructure.dto.ProductRequest;
import com.ecommerce.product_service.infrastructure.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductRequest req) {
        Product product = new Product();
        product.setName(req.name());
        product.setDescription(req.description());
        product.setPrice(req.price());
        product.setStock(req.stock());
        product.setCategory(req.category());

        Product saved = productUseCase.create(product);
        return toResponse(saved);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable UUID id, @RequestBody ProductRequest req) {
        Product product = new Product();
        product.setName(req.name());
        product.setDescription(req.description());
        product.setPrice(req.price());
        product.setStock(req.stock());
        product.setCategory(req.category());

        return toResponse(productUseCase.update(id, product));
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable UUID id) {
        return toResponse(productUseCase.getById(id));
    }

    @GetMapping
    public PageResponse<ProductResponse> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Product> data = productUseCase.list(PageRequest.of(page, size));
        return new PageResponse<>(
                data.map(this::toResponse).getContent(),
                data.getNumber(), data.getSize(),
                data.getTotalElements(), data.getTotalPages()
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        productUseCase.delete(id);
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getStock(),
                p.getCategory(),
                p.getCreatedAt()
        );
    }
}
