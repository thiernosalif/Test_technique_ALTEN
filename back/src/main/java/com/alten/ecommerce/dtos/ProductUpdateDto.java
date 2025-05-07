package com.alten.ecommerce.dtos;

import lombok.Data;

@Data
public class ProductUpdateDto {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String image;
    private String category;


}
