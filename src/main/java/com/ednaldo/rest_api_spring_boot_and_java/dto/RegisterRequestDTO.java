package com.ednaldo.rest_api_spring_boot_and_java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class RegisterRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String role;
}
