package com.alten.ecommerce.Exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Produit non trouvé avec l'ID: " + id);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
