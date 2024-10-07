package com.RaazDk.eComs.controller;

import com.RaazDk.eComs.models.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    List<Category> categories = new ArrayList<>();
    @GetMapping("/api/public/categories")
    public List<Category> getCategories(){
        return categories;
    }

    @PostMapping("/api/admin/addcategory")
    public String addCategory(@RequestBody Category category){
        categories.add(category);
        return category.getCategoryId().toString();
    }


}
