package com.ednaldo.rest_api_spring_boot_and_java.exceptions.handlecontroleexception;

import com.ednaldo.rest_api_spring_boot_and_java.exceptions.EmailAlreadyExistsException;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.InvalidFormatEmailException;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.InvalidJwtAuhthenticationException;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.ResourceNotFoundException;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHendler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<StandardError> emailExistException(EmailAlreadyExistsException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(InvalidFormatEmailException.class)
    public ResponseEntity<StandardError> emailFormatInvalidException(InvalidFormatEmailException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(InvalidJwtAuhthenticationException.class)
    public ResponseEntity<StandardError> AuhthenticationException(InvalidJwtAuhthenticationException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
//    @ExceptionHandler(DataBaseException.class)
//    public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest request) {
//        String error = "Database error";
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
//        return ResponseEntity.status(status).body(standardError);
//    }
}
