package com.trofimov.shop.entities;

import com.trofimov.shop.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private LinkedList<Order> orders;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
