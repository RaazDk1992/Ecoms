package com.RaazDk.eComs.controller;

import com.RaazDk.eComs.models.Category;
import com.RaazDk.eComs.services.CategoryService;
import com.RaazDk.eComs.services.CategoryServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/public/categories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/hello")
    public String sayHi(){
        return  "Hi";
    }

    @PostMapping("/api/admin/addcategory")
    public String addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return category.getCategoryId().toString();
    }


}
