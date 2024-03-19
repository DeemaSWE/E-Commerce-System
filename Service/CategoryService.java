package com.example.ecommerce.Service;

import com.example.ecommerce.Exception.CategoryNotFoundException;
import com.example.ecommerce.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void updateCategory(Category updatedCategory, String id) {
        Category category = getCategoryById(id);
        categories.set(categories.indexOf(category), updatedCategory);
    }

    public void deleteCategory(String id) {
        Category category = getCategoryById(id);
        categories.remove(category);
    }

    public Category getCategoryById(String id) {
        for (Category category : categories) {
            if (category.getId().equals(id))
                return category;
        }
        throw new CategoryNotFoundException("Category with id " + id + " not found");
    }
}
