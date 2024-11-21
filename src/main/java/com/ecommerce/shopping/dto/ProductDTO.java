package com.ecommerce.shopping.dto;

import com.ecommerce.shopping.model.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String brand;

    private Category category;


    private List<ImageDTO> images;
}
