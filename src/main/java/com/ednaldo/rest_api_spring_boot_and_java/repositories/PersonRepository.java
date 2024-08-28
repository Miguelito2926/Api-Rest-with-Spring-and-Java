package com.ednaldo.rest_api_spring_boot_and_java.repositories;

import com.ednaldo.rest_api_spring_boot_and_java.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
