package com.ednaldo.rest_api_spring_boot_and_java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String role;
}
