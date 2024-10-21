package com.RaazDk.eComs.services;

import com.RaazDk.eComs.models.Category;
import com.RaazDk.eComs.models.Product;
import com.RaazDk.eComs.repository.CategoryRepository;
import com.RaazDk.eComs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIml  implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public String addProduct(Product product) {
        Category cat = categoryRepository.findById(product.getCategory().getCategoryId())
           .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category could not be found!!"));
        product.setCategory(cat);
        productRepository.save(product);
        return ("Item added");
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
        return productRepository.findAll();
    }
}
