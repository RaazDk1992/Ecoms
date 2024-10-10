package com.RaazDk.eComs.services;

import com.RaazDk.eComs.models.Category;
import com.RaazDk.eComs.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIml  implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    List<Category> categories = new ArrayList<>();
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "Added item";
    }

    @Override
    public String updateCategory(Category categoryToUpdate, Long catId) {
        Optional<Category> savedCategory = categoryRepository.findById(catId);
        Category category = savedCategory.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Item not found!!"));
        categoryRepository.save(categoryToUpdate);
        return "CategoryId"+catId+" Updated to "+categoryToUpdate.getCategoryName();
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findAll().stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item not found!!"));
        categoryRepository.delete(category);
        return "Category with Id"+category.getCategoryId()+" Deleted.";
    }
}
