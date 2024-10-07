package com.RaazDk.eComs.services;

import com.RaazDk.eComs.models.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceIml  implements CategoryService{

    List<Category> categories = new ArrayList<>();
    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public String addCategory(Category category) {
        categories.add(category);
        return "Added item";
    }

    @Override
    public String updateCategory(Category category) {
        return "";
    }
}
