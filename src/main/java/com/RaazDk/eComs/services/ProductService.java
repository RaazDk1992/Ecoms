package com.RaazDk.eComs.services;

import com.RaazDk.eComs.models.Product;

import java.util.List;

public interface ProductService {
    public String addProduct(Product product);
    public String editProduct(Product product);
    public String deleteProduct(Long productId);
    public List<Product> getProducts();
}
