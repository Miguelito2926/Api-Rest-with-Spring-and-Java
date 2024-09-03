package com.ednaldo.rest_api_spring_boot_and_java.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class AccountCredentialsDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
}
