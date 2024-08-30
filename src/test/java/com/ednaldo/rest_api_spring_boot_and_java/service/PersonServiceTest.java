//package com.ednaldo.rest_api_spring_boot_and_java.service;
//
//import com.ednaldo.rest_api_spring_boot_and_java.dto.PersonDTO;
//import com.ednaldo.rest_api_spring_boot_and_java.entities.Person;
//import com.ednaldo.rest_api_spring_boot_and_java.repositories.PersonRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//class PersonServiceTest {
//
//    PersonDTO input;
//
//    @InjectMocks
//    private PersonService service;
//
//    @Mock
//    private PersonRepository personRepository;
//
//    @BeforeEach
//    void setUpMocks() {
//        input = new PersonDTO();
//        MockitoAnnotations.openMocks(this);
//
//    }
//
//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void create() {
//        PersonDTO person = input;
//    }
//
//    @Test
//    void findById() {
//        Person person = new Person();
//        person.setId(input.getId());
//        person.setFirstName(input.getFirstName());
//        person.setLastName(input.getLastName());
//        person.setAddress(input.getAddress());
//        person.setGender(input.getGender());
//
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
//}