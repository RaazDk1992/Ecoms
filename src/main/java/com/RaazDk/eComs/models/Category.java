package com.RaazDk.eComs.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 * Category Class
 */
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long CategoryId;
    String CategoryName;
}
