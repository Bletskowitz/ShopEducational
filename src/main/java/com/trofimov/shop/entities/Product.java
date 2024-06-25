package com.trofimov.shop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private Float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Category category;

    public Product(String name, Float price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

}
