package com.ednaldo.rest_api_spring_boot_and_java.repositories;

import com.ednaldo.rest_api_spring_boot_and_java.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
