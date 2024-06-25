package com.trofimov.shop.repositories;

import com.trofimov.shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findFirstById(Integer id);
    User findFirstByUsername(String username);
}
