package com.ecommerce.shopping.service.category;

import com.ecommerce.shopping.exception.ResourceNotFoundException;
import com.ecommerce.shopping.model.Category;
import com.ecommerce.shopping.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

//    public CategoryService(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found")) ;
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Category category, long id) {
        return Optional.ofNullable(getCategoryById(id)).map(oldCategory ->{
            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
        }).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository ::delete, () -> {
                    throw new ResourceNotFoundException("Category not found");
        });

    }
}
