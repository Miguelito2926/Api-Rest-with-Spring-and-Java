package com.ednaldo.rest_api_spring_boot_and_java.service;

import com.ednaldo.rest_api_spring_boot_and_java.dto.PersonDTO;
import com.ednaldo.rest_api_spring_boot_and_java.entities.Person;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.ResourceNotFoundException;
import com.ednaldo.rest_api_spring_boot_and_java.repositories.PersonRepository;
import com.ednaldo.rest_api_spring_boot_and_java.validator.EmailValidation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PersonService {

    private static Logger logger = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final EmailValidation emailValidation;

    public List<PersonDTO> findAll() {
        List<Person> persons = personRepository.findAll();

        return persons.stream()
                .map(person -> modelMapper.map(person, PersonDTO.class))
                .collect(Collectors.toList());
    }

    public PersonDTO create(PersonDTO personDTO) {

        Person person = modelMapper.map(personDTO, Person.class);
        person.setEmail(emailValidation.normalizeAndValidateEmail(person.getEmail()));
        person = personRepository.save(person);
        return modelMapper.map(person, PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID: " + id));
        return modelMapper.map(person, PersonDTO.class);
    }

    public PersonDTO update(PersonDTO personDTO, Long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID: " + id));

        if (!person.getEmail().equalsIgnoreCase(personDTO.getEmail())) {
            person.setEmail(emailValidation.normalizeAndValidateEmail(personDTO.getEmail()));
        }

        updateData(person, personDTO);
        person = personRepository.save(person);

        return modelMapper.map(person, PersonDTO.class);
    }

    public void delete(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID: " + id));
        personRepository.delete(person);
    }
    @Transactional
    public PersonDTO disable(Long id) {
        logger.info("Inativa registro de uma Pessoa!");

        // Primeiro verifica se a pessoa existe
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID: " + id));

        // Executa a query para desativar
        personRepository.disablePerson(id);

        // Atualiza o estado no objeto carregado ou recarrega se necessário
        person.setEnabled(false);

        return modelMapper.map(person, PersonDTO.class);
    }


    private void updateData(Person person, PersonDTO personDTO) {
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setEmail(personDTO.getEmail().toLowerCase());
        person.setPhone(personDTO.getPhone());
        person.setAddress(personDTO.getAddress());
        person.setGender(personDTO.getGender());
        person.setBirthDate(personDTO.getBirthDate());
    }
}
