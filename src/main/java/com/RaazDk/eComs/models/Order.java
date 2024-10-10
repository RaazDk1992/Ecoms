package com.RaazDk.eComs.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Date orderDate;
    private int quantity;
    @OneToMany @JoinColumn(name = "customer_ref")
    private List<Customer> customer;
    @OneToMany @JoinColumn(name = "cart_ref")
    private List<Cart> cart;
}
