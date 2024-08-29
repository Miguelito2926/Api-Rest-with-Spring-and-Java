package com.ednaldo.rest_api_spring_boot_and_java.controller;


import com.ednaldo.rest_api_spring_boot_and_java.dto.PersonDTO;
import com.ednaldo.rest_api_spring_boot_and_java.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PersonDTO>> findAll() {
         List<PersonDTO> list = personService.findAll();
         return ResponseEntity.ok().body(list);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO personDTO) {
        PersonDTO personCreated = personService.create(personDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        PersonDTO personDTO = personService.findById(id);
        return ResponseEntity.ok().body(personDTO);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        PersonDTO PersonDTO = personService.update(personDTO, id);
        return  ResponseEntity.ok().body(PersonDTO);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
