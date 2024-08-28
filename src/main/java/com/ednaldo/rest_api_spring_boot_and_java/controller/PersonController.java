package com.ednaldo.rest_api_spring_boot_and_java.controller;

import com.ednaldo.rest_api_spring_boot_and_java.model.Person;
import com.ednaldo.rest_api_spring_boot_and_java.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
         List<Person> list = personService.findAll();
         return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        Person personCreated = personService.create(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
    }
}
