package com.alten.ecommerce.mapper;

import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.entities.Product;
import com.alten.ecommerce.enums.InventoryStatus;

public class ProductMapper {

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setCode(product.getCode());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setInternalReference(product.getInternalReference());
        dto.setShellId(product.getShellId());
        dto.setInventoryStatus(product.getInventoryStatus());
        dto.setRating(product.getRating());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }

    public static Product fromDto(ProductDto dto) {
        Product p = new Product();
        p.setId(dto.getId());
        p.setCode(dto.getCode());
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setImage(dto.getImage());
        p.setCategory(dto.getCategory());
        p.setPrice(dto.getPrice());
        p.setQuantity(dto.getQuantity());
        p.setInternalReference(dto.getInternalReference());
        p.setShellId(dto.getShellId());
       // p.setInventoryStatus(Product.InventoryStatus.valueOf(dto.getInventoryStatus().name()));
        p.setInventoryStatus(dto.getInventoryStatus());
        p.setRating(dto.getRating());
        p.setCreatedAt(dto.getCreatedAt());
        p.setUpdatedAt(dto.getUpdatedAt());
        return p;
    }
}
