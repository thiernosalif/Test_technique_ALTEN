package com.alten.ecommerce.services;

import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.dtos.ProductUpdateDto;
import com.alten.ecommerce.entities.Product;

import java.util.List;

public interface ProductService {

    /**
     * Save a  ProductDto object
     *
     * @param  product dto object
     * @return The stored { ProductDto} object
     */
    public ProductDto create(ProductDto product);

    /**
     * Find all {ProductDto} objects
     *
     * @return The list of {ProductDto} objects
     */
    List<ProductDto> findAllProducts();


    /**
     * Find By id a {ProductDto} object by its id
     *
     * @param id The id of the {ProductDto} object
     * @return The found {ProductDto} object
     */
    ProductDto findProductById(Long id);

    /**
     * Delete a {ProductDto} object
     *
     * @param id The id of the {ProductDto} object
     */
    void deleteProduct(Long id);

    /**
     * Update a {ProductDto} object
     *
     * @param id The id of the {Product} object
     */
    Product updateProduct(Long id, ProductUpdateDto dto);
}
