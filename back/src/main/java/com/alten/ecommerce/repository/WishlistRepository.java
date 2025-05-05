package com.alten.ecommerce.services.impl;

import com.alten.ecommerce.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUserEmail(String email);
}
