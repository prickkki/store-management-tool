package com.store.management.repository;

import com.store.management.entity.StoreUnitNomenclator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreUnitRepository extends JpaRepository<StoreUnitNomenclator, Long> {
    boolean existsByName(String name);
}

