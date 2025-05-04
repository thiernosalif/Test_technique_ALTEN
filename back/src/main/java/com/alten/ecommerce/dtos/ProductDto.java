package com.alten.ecommerce.dtos;

import com.alten.ecommerce.enums.InventoryStatus;


import java.time.Instant;

public class ProductDto {

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
    private InventoryStatus inventoryStatus;

    private int rating;
    private Instant createdAt;
    private Instant updatedAt;
}
