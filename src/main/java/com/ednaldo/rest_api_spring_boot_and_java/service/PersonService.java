package com.ednaldo.rest_api_spring_boot_and_java.service;

import com.ednaldo.rest_api_spring_boot_and_java.dto.PersonDTO;
import com.ednaldo.rest_api_spring_boot_and_java.entities.Person;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.ResourceNotFoundException;
import com.ednaldo.rest_api_spring_boot_and_java.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;  // Injetando o ModelMapper como bean

    public List<PersonDTO> findAll() {
        List<Person> persons = personRepository.findAll();
        // Converte a lista de Person para PersonDTO usando o ModelMapper
        return persons.stream()
                .map(person -> modelMapper.map(person, PersonDTO.class))
                .collect(Collectors.toList());
    }

    public PersonDTO create(PersonDTO personDTO) {
        // Converte o DTO para entidade Person
        Person person = modelMapper.map(personDTO, Person.class);
        person = personRepository.save(person);
        // Converte a entidade salva para DTO
        return modelMapper.map(person, PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: " + id));
        // Converte a entidade encontrada para DTO
        return modelMapper.map(person, PersonDTO.class);
    }

    public PersonDTO update(PersonDTO personDTO, Long id) {
        try {
            Person person = personRepository.getReferenceById(id);
            updateData(person, personDTO);
            person = personRepository.save(person);
            // Converte a entidade atualizada para DTO
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

    private void updateData(Person person, PersonDTO personDTO) {
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setAddress(personDTO.getAddress());
        person.setGender(personDTO.getGender());
    }
}
