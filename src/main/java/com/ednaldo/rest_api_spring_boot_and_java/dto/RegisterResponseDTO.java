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
    private String password;
    private Set<Role> role;

    public RegisterResponseDTO(String username, String password, Set<Role> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
