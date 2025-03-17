package com.store.management.repository;

import com.store.management.entity.VendorNomenclator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorNomenclator, Long> {
    boolean existsByName(String name);
}

