package com.ednaldo.rest_api_spring_boot_and_java.repositories;

import com.ednaldo.rest_api_spring_boot_and_java.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByName(String name);
}

