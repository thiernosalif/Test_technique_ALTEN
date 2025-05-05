package com.alten.ecommerce.controllers;

import com.alten.ecommerce.controllers.apis.WishlistController;
import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.entities.Product;
import com.alten.ecommerce.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
public class WishlistControllerImpl implements WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Override
    public void addToWishlist(Long productId, Principal principal) {
        wishlistService.addProductToWishlist(principal.getName(), productId);

    }

    @Override
    public Set<Product> getWishlist(Principal principal) {
        return wishlistService.getWishlistProducts(principal.getName());
    }
}
