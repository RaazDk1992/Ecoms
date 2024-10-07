package com.RaazDk.eComs.services;

import com.RaazDk.eComs.models.Category;

import java.util.List;

public interface CategoryService {
    public List<Category>getCategories();
    public String addCategory(Category category);
    public String updateCategory(Category category);
}
