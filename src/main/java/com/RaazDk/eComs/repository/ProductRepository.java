package com.RaazDk.eComs.repository;

import com.RaazDk.eComs.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product,Long> {
}
