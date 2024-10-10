package com.RaazDk.eComs.repository;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor@AllArgsConstructor
@Data
/**\
 *  Class has one-to-many mapping from category->Product.
 */
public class Product {

    Long productId;
    String productName;
    int price;
    int quantity;

}
