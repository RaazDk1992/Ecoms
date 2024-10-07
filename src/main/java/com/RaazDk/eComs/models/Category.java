package com.RaazDk.eComs.models;

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

    Long CategoryId;
    String CategoryName;
}
