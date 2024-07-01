package com.trofimov.shop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "order_pos")
@NoArgsConstructor
@Getter
@Setter
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", unique = false)
    private Product product;

    private Integer amount;
}
