package com.trofimov.shop.repositories;

import com.trofimov.shop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
    Order findFirstById(Integer id);
}
