package com.store.management.repository;

import com.store.management.entity.CategoryNomenclator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryNomenclator, Long> {
    boolean existsByName(String name);
}

