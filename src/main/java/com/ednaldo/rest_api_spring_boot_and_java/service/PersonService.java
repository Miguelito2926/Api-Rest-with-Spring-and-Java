package com.ednaldo.rest_api_spring_boot_and_java.service;

import com.ednaldo.rest_api_spring_boot_and_java.model.Person;
import com.ednaldo.rest_api_spring_boot_and_java.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> findAll() {
         List<Person> list = personRepository.findAll();
         return list;
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }
}
