package com.RaazDk.eComs.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Category {

    public Long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Long categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public Category(Long categoryId, String categoryName) {
        CategoryId = categoryId;
        CategoryName = categoryName;
    }

    @Id
    Long CategoryId;
    String CategoryName;
}
