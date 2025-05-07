package com.alten.ecommerce.controllers;

import com.alten.ecommerce.controllers.apis.ProductController;
import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.dtos.ProductUpdateDto;
import com.alten.ecommerce.entities.Product;
import com.alten.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class ProductControllerImpl implements ProductController {


    private final ProductService productService;



    @Override
    public ResponseEntity<ProductDto> createProduct(ProductDto productDto) {

        ProductDto aProductDto = productService.create(productDto);
        return new ResponseEntity<>(aProductDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        List<ProductDto> aProductDtoS = productService.findAllProducts();
        return new ResponseEntity<>(aProductDtoS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> findProductById(Long id) {
        ProductDto aProductDto = productService.findProductById(id);
        return new ResponseEntity<>(aProductDto, HttpStatus.OK);
    }

    @Override
    public void deleteProduct(Long id) {
        productService.deleteProduct(id);

    }

    @Override
    public ResponseEntity<?> updateProduct(Long id, ProductUpdateDto dto) {
        Product updatedProduct = productService.updateProduct(id, dto);
        return ResponseEntity.ok(updatedProduct);
    }
}
