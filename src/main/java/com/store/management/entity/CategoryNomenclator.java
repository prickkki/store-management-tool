package com.store.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category_nomenclator")
public class CategoryNomenclator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "discount")
    private Double discount;

}
