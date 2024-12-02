package com.ecommerce.shopping.request;

import com.ecommerce.shopping.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String brand;
    private Category category;
}
