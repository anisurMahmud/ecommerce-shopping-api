package com.ecommerce.shopping.service.product;

import com.ecommerce.shopping.model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    Product getProductById(long id);
    void deleteProductById(long id);
    void updateProductById(Product product, long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
