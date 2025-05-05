package com.alten.ecommerce;

import com.alten.ecommerce.entities.Product;
import com.alten.ecommerce.entities.User;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.alten.ecommerce.enums.InventoryStatus.*;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataLoader(ProductRepository productRepository, UserRepository userRepository
            , BCryptPasswordEncoder passwordEncoder) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            productRepository.save(new Product(null, "P001", "Produit A", "Description produit A", "imageA.jpg", "Catégorie 1", 29.99, 100, "refA001", 1L, INSTOCK, 4, Instant.now(), Instant.now()));
            productRepository.save(new Product(null, "P002", "Produit B", "Description produit B", "imageB.jpg", "Catégorie 2", 49.99, 50, "refB002", 2L, LOWSTOCK, 4, Instant.now(), Instant.now()));
            productRepository.save(new Product(null, "P003", "Produit C", "Description produit C", "imageC.jpg", "Catégorie 3", 19.99, 200, "refC003", 3L, OUTOFSTOCK, 3, Instant.now(), Instant.now()));
        }

        if (userRepository.count() == 0) {
            userRepository.save(new User("admin", "admin", "admin@admin.com", passwordEncoder.encode("password")));
            userRepository.save(new User("user1", "user1","user1@example.com", passwordEncoder.encode("password")));
            userRepository.save(new User("user2","user2","user2@example.com",  passwordEncoder.encode("password")));
        }
    }
}

