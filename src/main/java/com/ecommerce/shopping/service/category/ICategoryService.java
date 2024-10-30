package com.ecommerce.shopping.service.category;

import com.ecommerce.shopping.model.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, long id);
    void deleteCategory(long id);
}
