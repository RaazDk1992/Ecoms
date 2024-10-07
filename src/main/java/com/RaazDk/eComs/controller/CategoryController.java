package com.RaazDk.eComs.controller;

import com.RaazDk.eComs.models.Category;
import com.RaazDk.eComs.services.CategoryService;
import com.RaazDk.eComs.services.CategoryServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<String> addCategory(@RequestBody Category category){
       try{
           String status= categoryService.addCategory(category);
           return new ResponseEntity<>(status,HttpStatus.OK);
       }catch (ResponseStatusException e){
           return new ResponseEntity<>(e.getReason(),e.getStatusCode());
       }

    }

    @DeleteMapping("/api/admin/delete/{id}")
    public ResponseEntity<String> deleteCategory(@RequestBody Long id){
        try{
            String status= categoryService.deleteCategory(id);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }

    }

}
