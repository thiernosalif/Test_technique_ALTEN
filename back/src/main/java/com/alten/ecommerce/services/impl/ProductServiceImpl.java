package com.alten.ecommerce.services.impl;

import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDto create(ProductDto product) {
        return null;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return List.of();
    }

    @Override
    public ProductDto findProductById(Long id) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
