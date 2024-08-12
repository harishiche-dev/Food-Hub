package com.tron.FoodHub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tron.FoodHub.entity.Category;
import com.tron.FoodHub.repo.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category saveCategory(Category category) {
    //	category.setCreatedOn(Timestamp);
        return categoryRepository.save(category);
    }
    
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
