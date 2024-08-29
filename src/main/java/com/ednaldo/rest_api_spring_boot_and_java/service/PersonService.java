package com.ednaldo.rest_api_spring_boot_and_java.service;

import com.ednaldo.rest_api_spring_boot_and_java.dto.PersonDTO;
import com.ednaldo.rest_api_spring_boot_and_java.entities.Person;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.ResourceNotFoundException;
import com.ednaldo.rest_api_spring_boot_and_java.repositories.PersonRepository;
import com.ednaldo.rest_api_spring_boot_and_java.utils.DozerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final DozerMapper dozerMapper = new DozerMapper(); // Instância do mapper

    public List<PersonDTO> findAll() {
        List<Person> persons = personRepository.findAll();
        return DozerMapper.parseListObjects(persons, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO personDTO) {
        Person person = DozerMapper.parseObject(personDTO, Person.class);
        person = personRepository.save(person);
        return dozerMapper.parseObject(person, PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: " + id));
        return DozerMapper.parseObject(person, PersonDTO.class);
    }

    public PersonDTO update(PersonDTO personDTO, Long id) {
        try {
            Person person = personRepository.getReferenceById(id);
            updateData(person, personDTO);
            person = personRepository.save(person);
            return DozerMapper.parseObject(person, PersonDTO.class);
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
