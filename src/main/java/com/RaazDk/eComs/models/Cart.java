package com.RaazDk.eComs.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Data
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long cartId;
    private int quantity;
    private int NetAmount;
    @OneToMany @JoinColumn(name = "product_ref")
    private List<Product> product;
    @OneToMany @JoinColumn(name = "customer_ref")
    private List<Customer> customer;



}
