package com.challenge.ecommerce.modules.products.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private int stock;
    private Boolean isActive;
    //private String image;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;
}
