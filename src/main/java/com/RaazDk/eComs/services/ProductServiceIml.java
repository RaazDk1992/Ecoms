package com.RaazDk.eComs.services;

import com.RaazDk.eComs.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceIml  implements ProductService{
    @Override
    public String addProduct(Product product) {
        return "";
    }

    @Override
    public String editProduct(Product product) {
        return "";
    }

    @Override
    public String deleteProduct(Long productId) {
        return "";
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }
}
