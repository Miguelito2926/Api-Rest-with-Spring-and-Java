package com.ednaldo.rest_api_spring_boot_and_java.validator;

import com.ednaldo.rest_api_spring_boot_and_java.exceptions.EmailAlreadyExistsException;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.InvalidFormatEmailException;
import com.ednaldo.rest_api_spring_boot_and_java.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailValidation {

    private final PersonRepository personRepository;

    public void validateEmailFormat(String email) throws InvalidFormatEmailException {

        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if(!email.matches(emailRegex)) {
            throw new InvalidFormatEmailException("Endereço de e-mail inválido.");
        }
    }

    public void validateEmailUnique(String email) {
        if(personRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Este e-mail já está cadastrado.");
        }
    }

    public String normalizeEmail(String email) {
        return email.toLowerCase();
    }

    public String normalizeAndValidateEmail(String email) {
        validateEmailFormat(email);
        validateEmailUnique(email);
        return normalizeEmail(email);
    }
}
