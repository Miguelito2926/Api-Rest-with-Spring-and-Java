package com.ednaldo.rest_api_spring_boot_and_java.repositories;

import com.ednaldo.rest_api_spring_boot_and_java.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUserName(@Param("username") String username);
}

