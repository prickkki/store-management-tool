package com.store.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_nomenclator")
public class VendorNomenclator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;

}
