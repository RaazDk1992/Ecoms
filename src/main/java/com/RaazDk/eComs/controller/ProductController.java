package com.RaazDk.eComs.controller;

import com.RaazDk.eComs.models.Category;
import com.RaazDk.eComs.models.Product;
import com.RaazDk.eComs.repository.CategoryRepository;
import com.RaazDk.eComs.services.CategoryService;
import com.RaazDk.eComs.services.ProductService;
import com.RaazDk.eComs.services.ProductServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Autowired
    private CategoryRepository categoryRepository;


    /**
     * Endpoint for getting all products details.
     */
    @GetMapping("/api/public/products")
    public String getCategoryList() {

        return "Hello there";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/admin/addproduct")
    public ResponseEntity<String> addProduct(
            @RequestParam("productName") String productName,
            @RequestParam("manufacturer") String manufacturer,
            @RequestParam("manufactureDate") String manufactureDate,
            @RequestParam("expiryDate") String expiryDate,
            @RequestParam("doesExpire") Boolean doesExpire,
            @RequestParam("quantity") int quantity,
            @RequestParam("price") int price,
            @RequestParam("minQuantity") int minQuantity,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("imageFile") MultipartFile imageFile
) {
        System.out.println("productName = " + productName);
       try{
           SimpleDateFormat fromat = new SimpleDateFormat("dd-MM-yyyy");
           Product product = new Product();
           product.setProductName(productName);
           product.setManufacturer(manufacturer);
           product.setManufactureDate(fromat.parse(manufactureDate));
           if(expiryDate != null && !expiryDate.isEmpty()){
               product.setExpiryDate(fromat.parse(expiryDate));

           }
           product.setDoesExpire(doesExpire);
           product.setQuantity(quantity);
           product.setPrice(price);
           product.setMinQuantity(minQuantity);
           Category cat = categoryRepository.findById(categoryId)
                   .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category could not be found!!"));
           product.setCategory(cat);
            if(imageFile !=null && !imageFile.isEmpty()){
                String filePath = "uploads";
                Path path = Paths.get(filePath);
                if(!Files.exists(path)){
                    Files.createDirectories(path);
                } else {
                    FileOutputStream fos = new FileOutputStream(filePath+"/"+imageFile.getOriginalFilename());
                    fos.write(imageFile.getBytes());
                    product.setImagePath(filePath);
                }


            }

           productService.addProduct(product);
           return new ResponseEntity<>(product.toString(),HttpStatus.OK);
       }catch(ResponseStatusException e){
           return new ResponseEntity<>(e.getReason(),e.getStatusCode());

       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       } catch (IOException | ParseException e) {
           throw new RuntimeException(e);
       }

    }
}

