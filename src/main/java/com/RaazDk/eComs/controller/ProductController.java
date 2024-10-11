package com.RaazDk.eComs.controller;

import com.RaazDk.eComs.models.Product;
import com.RaazDk.eComs.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller takes care of http requests coming from client side and
 * calls service layer methods.
 */
@RestController
public class ProductController {
    /**
     * Service Responsible for managing categories.
     */
    @Autowired
    private ProductService productService;

    /**
     * Endpoint for getting all products details.
     */
    @GetMapping("/api/public/products")
    public String getCategoryList() {

        return "Hello there";
    }

    @PostMapping("/api/admin/addproduct")
    public ResponseEntity<String> addProduct(@RequestBody  Product product){
       try{
           productService.addProduct(product);
           return new ResponseEntity<>("Item Added",HttpStatus.OK);
       }catch(ResponseStatusException e){
           return new ResponseEntity<>(e.getReason(),e.getStatusCode());

       }
        
    }
}

