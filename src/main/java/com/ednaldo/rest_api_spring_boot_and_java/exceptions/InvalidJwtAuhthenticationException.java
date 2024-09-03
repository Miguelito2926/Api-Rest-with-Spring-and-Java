package com.ednaldo.rest_api_spring_boot_and_java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuhthenticationException extends AuthenticationException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidJwtAuhthenticationException(String e) {
        super(e);
    }
}
