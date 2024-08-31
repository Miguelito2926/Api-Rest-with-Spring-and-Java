package com.ednaldo.rest_api_spring_boot_and_java.validator;

import com.ednaldo.rest_api_spring_boot_and_java.exceptions.EmailAlreadyExistsException;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.InvalidFormatEmailException;
import com.ednaldo.rest_api_spring_boot_and_java.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailValidation {

    public static void validateEmailFormat(String email) throws InvalidFormatEmailException {

        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if(!email.matches(emailRegex)) {
            throw new InvalidFormatEmailException("Endereço de e-mail inválido.");
        };
    }
}
