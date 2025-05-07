package com.alten.ecommerce.controllers;

import com.alten.ecommerce.controllers.apis.CartController;
import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.entities.Product;
import com.alten.ecommerce.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@AllArgsConstructor
@RestController
public class CartControllerImpl implements CartController {

    @Autowired
    private CartService cartService;

    @Override
    public void addToCart(Long productId, Principal principal) {
        cartService.addProductToCart(principal.getName(), productId);
    }

    @Override
    public Set<Product> getCart(Principal principal) {
        return cartService.getCartProducts(principal.getName());
    }

    @Override
    public ResponseEntity<String> removeProductFromCart(Long productId, Principal principal) {
        try {
            cartService.removeProductFromCart(principal.getName(), productId);
            return ResponseEntity.ok("Produit supprimé du panier");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
