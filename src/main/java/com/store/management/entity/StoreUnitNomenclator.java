package com.store.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "store_unit_nomenclator")
public class StoreUnitNomenclator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "manager")
    private String manager;

    @Column(name = "location")
    private String location;

    @Column(name = "capacity")
    private Integer capacity;

}

