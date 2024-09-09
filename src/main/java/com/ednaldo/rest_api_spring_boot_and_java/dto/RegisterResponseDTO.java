package com.ednaldo.rest_api_spring_boot_and_java.dto;

import com.ednaldo.rest_api_spring_boot_and_java.entities.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Data
public class RegisterResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private Set<Role> role;

    public RegisterResponseDTO(String username,  Set<Role> role) {
        this.username = username;
        this.role = role;
    }
}
