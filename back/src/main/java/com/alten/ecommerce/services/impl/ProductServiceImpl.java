package com.alten.ecommerce.services.impl;

import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.dtos.ProductUpdateDto;
import com.alten.ecommerce.entities.Product;
import com.alten.ecommerce.mapper.ProductMapper;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    private ProductMapper from;

    @Override
    public ProductDto create(ProductDto product) {

        if (product == null) {
            throw new IllegalArgumentException("Le produit ne peut pas être null");
        }

        Product p = ProductMapper.fromDto(product);
        product.setCreatedAt(System.currentTimeMillis());
        product.setUpdatedAt(System.currentTimeMillis());

        Product savedProduct = productRepository.save(p);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public List<ProductDto> findAllProducts() {

        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l'id : " + id));
        return ProductMapper.toDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Impossible de supprimer : produit introuvable avec l'id " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, ProductUpdateDto dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé pour mise à jour avec l'id : " + id));

        if (dto.getName() != null) product.setName(dto.getName());
        if (dto.getDescription() != null) product.setDescription(dto.getDescription());
        if (dto.getPrice() != null) product.setPrice(dto.getPrice());
        if (dto.getQuantity() != null) product.setQuantity(dto.getQuantity());
        if (dto.getCategory() != null) product.setCategory(dto.getCategory());

        product.setUpdatedAt(System.currentTimeMillis());

        return productRepository.save(product);

}


}
