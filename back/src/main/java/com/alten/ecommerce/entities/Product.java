package com.alten.ecommerce.entities;

import com.alten.ecommerce.enums.InventoryStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "cartItems")
@EqualsAndHashCode(exclude = "cartItems")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private Long shellId;
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;

    private int rating;
    private long createdAt;
    private long updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<CartItem> cartItems = new HashSet<>();

 /*   @Version
    private Integer version;*/

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setProduct(this);
    }

    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        cartItem.setProduct(null);
    }
}
