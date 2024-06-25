package com.trofimov.shop.repositories;

import com.trofimov.shop.entities.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionsRepository extends JpaRepository<OrderPosition, Integer> {
}
