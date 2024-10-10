package com.RaazDk.eComs.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Data
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String customerName;
    private String shippingAddress;
    private String telephone;
    @Column(name = "e-mail")
    private String email;


}
