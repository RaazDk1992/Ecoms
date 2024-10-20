package com.RaazDk.eComs.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Data
/**
 *
 * Class stores the product details including manufacturer, whether it expires and expiry date.
 */
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long productId;
    String productName;
    String manufacturer;
    String imagePath;
    Date manufactureDate;
    Date expiryDate;
    Boolean doesExpire;
    int quantity;
    int price;
    int minQuantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_ref")
    private Category category;

}
