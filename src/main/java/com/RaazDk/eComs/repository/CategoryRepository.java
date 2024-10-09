package com.RaazDk.eComs.repository;

import com.RaazDk.eComs.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
