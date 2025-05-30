package com.bazarweb.bazarweb.service.Catalog;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bazarweb.bazarweb.model.Catalog.Category;
import com.bazarweb.bazarweb.repository.Catalog.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public Category categoryCreate(Category category){
        return save(category);
    }

    public Category getCategoryById(int id){
            return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category getByName(String name){
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void updateCategory(int id, Category updatedCategory) {
        var category = getCategoryById(id);
        category.setName(updatedCategory.getName());
        categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

}
