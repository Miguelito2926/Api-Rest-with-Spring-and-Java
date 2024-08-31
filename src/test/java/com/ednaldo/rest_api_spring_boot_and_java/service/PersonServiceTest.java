//package com.ednaldo.rest_api_spring_boot_and_java.service;
//
//import com.ednaldo.rest_api_spring_boot_and_java.dto.PersonDTO;
//import com.ednaldo.rest_api_spring_boot_and_java.entities.Person;
//import com.ednaldo.rest_api_spring_boot_and_java.exceptions.EmailAlreadyExistsException;
//import com.ednaldo.rest_api_spring_boot_and_java.exceptions.ResourceNotFoundException;
//import com.ednaldo.rest_api_spring_boot_and_java.repositories.PersonRepository;
//import com.ednaldo.rest_api_spring_boot_and_java.validator.EmailValidation;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class PersonServiceTest {
//
//    @InjectMocks
//    private PersonService personService;
//
//    @Mock
//    private PersonRepository personRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @Mock
//    private EmailValidation emailValidation; // Adicionando o mock de EmailValidation
//
//    private Person person;
//    private PersonDTO personDTO;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        // Inicializa o objeto PersonDTO com os mesmos atributos
//        personDTO = new PersonDTO();
//        personDTO.setFirstName("John");
//        personDTO.setLastName("Doe");
//        personDTO.setEmail("john.doe@example.com");
//        personDTO.setPhone("123-456-7890");
//        personDTO.setAddress("123 Main St");
//        personDTO.setGender("Male");
//        personDTO.setBirthDate(LocalDate.of(1990, 1, 1).atStartOfDay());
//
//        // Inicializa o objeto Person com todos os atributos
//        person = new Person();
//        person.setId(1L);
//        person.setFirstName("John");
//        person.setLastName("Doe");
//        person.setEmail("john.doe@example.com");
//        person.setPhone("123-456-7890");
//        person.setAddress("123 Main St");
//        person.setGender("Male");
//        person.setBirthDate(LocalDate.of(1990, 1, 1).atStartOfDay());
//        person.setCreatedAt(LocalDateTime.now());
//        person.setUpdatedAt(LocalDateTime.now());
//    }
//
//    @Test
//    void testCreatePersonSuccess() {
//        // Mocking the email validation and normalization
//        when(emailValidation.normalizeAndValidateEmail(personDTO.getEmail())).thenReturn(personDTO.getEmail());
//        when(modelMapper.map(personDTO, Person.class)).thenReturn(person);
//        when(personRepository.existsByEmail(personDTO.getEmail())).thenReturn(false);
//        when(personRepository.save(any(Person.class))).thenReturn(person);
//        when(modelMapper.map(person, PersonDTO.class)).thenReturn(personDTO);
//
//        PersonDTO result = personService.create(personDTO);
//
//        assertNotNull(result);
//        assertEquals(personDTO.getFirstName(), result.getFirstName());
//        assertEquals(personDTO.getLastName(), result.getLastName());
//        assertEquals(personDTO.getEmail(), result.getEmail());
//        assertEquals(personDTO.getPhone(), result.getPhone());
//        assertEquals(personDTO.getAddress(), result.getAddress());
//        assertEquals(personDTO.getGender(), result.getGender());
//        assertEquals(personDTO.getBirthDate(), result.getBirthDate());
//
//        verify(personRepository, times(1)).save(person);
//    }
//
//    @Test
//    void testCreatePersonThrowsEmailAlreadyExistsException() {
//        // Mocking the email validation and normalization
//        when(emailValidation.normalizeAndValidateEmail(personDTO.getEmail())).thenReturn(personDTO.getEmail());
//        when(personRepository.existsByEmail(personDTO.getEmail())).thenReturn(true);
//
//        assertThrows(EmailAlreadyExistsException.class, () -> personService.create(personDTO));
//
//        verify(personRepository, never()).save(any(Person.class));
//    }
//
//    @Test
//    void testFindByIdSuccess() {
//        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
//        when(modelMapper.map(person, PersonDTO.class)).thenReturn(personDTO);
//
//        PersonDTO result = personService.findById(1L);
//
//        assertNotNull(result);
//        assertEquals(personDTO.getFirstName(), result.getFirstName());
//        assertEquals(personDTO.getLastName(), result.getLastName());
//        assertEquals(personDTO.getEmail(), result.getEmail());
//        assertEquals(personDTO.getPhone(), result.getPhone());
//        assertEquals(personDTO.getAddress(), result.getAddress());
//        assertEquals(personDTO.getGender(), result.getGender());
//        assertEquals(personDTO.getBirthDate(), result.getBirthDate());
//
//        verify(personRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void testFindByIdThrowsResourceNotFoundException() {
//        when(personRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> personService.findById(1L));
//    }
//
//    @Test
//    void testUpdatePersonSuccess() {
//        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
//        when(personRepository.save(any(Person.class))).thenReturn(person);
//        when(modelMapper.map(person, PersonDTO.class)).thenReturn(personDTO);
//
//        PersonDTO result = personService.update(personDTO, 1L);
//
//        assertNotNull(result);
//        assertEquals(personDTO.getFirstName(), result.getFirstName());
//        assertEquals(personDTO.getEmail(), result.getEmail());
//        assertEquals(personDTO.getPhone(), result.getPhone());
//        assertEquals(personDTO.getAddress(), result.getAddress());
//        assertEquals(personDTO.getGender(), result.getGender());
//        assertEquals(personDTO.getBirthDate(), result.getBirthDate());
//
//        verify(personRepository, times(1)).save(person);
//    }
//
//    @Test
//    void testDeletePersonSuccess() {
//        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
//
//        personService.delete(1L);
//
//        verify(personRepository, times(1)).delete(person);
//    }
//
//    @Test
//    void testDeletePersonThrowsResourceNotFoundException() {
//        when(personRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> personService.delete(1L));
//    }
//}
