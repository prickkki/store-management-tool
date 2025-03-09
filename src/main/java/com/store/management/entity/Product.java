package com.store.management.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "specification")
    private String specification;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryNomenclator category;

    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private VendorNomenclator vendor;

    @ManyToOne
    @JoinColumn(name = "store_unit_id", referencedColumnName = "id")
    private StoreUnitNomenclator storeUnit;

    @Column(name = "price_vendor", nullable = false)
    private Double priceVendor;

    @Column(name = "selling_price", nullable = false)
    private Double sellingPrice;

    @Column(name = "discount", nullable = false)
    private Double discount;
}

