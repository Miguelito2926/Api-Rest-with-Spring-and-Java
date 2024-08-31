package com.ednaldo.rest_api_spring_boot_and_java.service;

import com.ednaldo.rest_api_spring_boot_and_java.dto.PersonDTO;
import com.ednaldo.rest_api_spring_boot_and_java.entities.Person;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.EmailAlreadyExistsException;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.ResourceNotFoundException;
import com.ednaldo.rest_api_spring_boot_and_java.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.ednaldo.rest_api_spring_boot_and_java.validator.EmailValidation.validateEmailFormat;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public List<PersonDTO> findAll() {
        List<Person> persons = personRepository.findAll();

        return persons.stream()
                .map(person -> modelMapper.map(person, PersonDTO.class))
                .collect(Collectors.toList());
    }

    public PersonDTO create(PersonDTO personDTO) {

        Person person = modelMapper.map(personDTO, Person.class);

        validateEmailFormat(personDTO.getEmail());
        validateEmailUnique(person.getEmail());
        person.setEmail(person.getEmail().toLowerCase());

        person = personRepository.save(person);
        return modelMapper.map(person, PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: " + id));

        return modelMapper.map(person, PersonDTO.class);
    }

    public PersonDTO update(PersonDTO personDTO, Long id) {
        try {

            Person person = personRepository.getReferenceById(id);
            validateEmailFormat(personDTO.getEmail());
            validateEmailUnique(person.getEmail());
            person.setEmail(person.getEmail().toLowerCase());
            updateData(person, personDTO);
            person = personRepository.save(person);

            return modelMapper.map(person, PersonDTO.class);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public void delete(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: " + id));
        personRepository.delete(person);
    }

    public void validateEmailUnique(String email) {
        if (personRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Este e-mail já está cadastrado.");
        }
    }

    private void updateData(Person person, PersonDTO personDTO) {
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setEmail(personDTO.getEmail().toLowerCase());
        person.setPhone(personDTO.getPhone());
        person.setAddress(personDTO.getAddress());
        person.setGender(personDTO.getGender());
        person.setBirthDate(personDTO.getBirthDate());
        person.setUpdatedAt(LocalDateTime.now());  // Atualiza a data de modificação
    }
}
