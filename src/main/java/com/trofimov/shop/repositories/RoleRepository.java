package com.trofimov.shop.repositories;

import com.trofimov.shop.entities.Role;
import com.trofimov.shop.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT rl FROM Role rl WHERE rl.name = :name")
    Role findFirstByName(UserRole name);

}
