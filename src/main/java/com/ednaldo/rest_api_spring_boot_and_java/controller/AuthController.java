package com.ednaldo.rest_api_spring_boot_and_java.controller;

import com.ednaldo.rest_api_spring_boot_and_java.dto.AccountCredentialsDTO;
import com.ednaldo.rest_api_spring_boot_and_java.dto.RegisterRequestDTO;
import com.ednaldo.rest_api_spring_boot_and_java.dto.RegisterResponseDTO;
import com.ednaldo.rest_api_spring_boot_and_java.dto.TokenDTO;
import com.ednaldo.rest_api_spring_boot_and_java.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;

@RequestMapping(value = "/auth")
@RequiredArgsConstructor
@RestController
@Tag(name = "Autenticação")
public class AuthController {

    private final UserService userService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Autentica um usuário e retorna um token")
    @PostMapping(value = "/signin")
    public ResponseEntity<TokenDTO> signin(@RequestBody AccountCredentialsDTO request) {
        TokenDTO userToken = userService.login(request);
        return ResponseEntity.ok(userToken);
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Cria um perfil e roles desse perfil")
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO data) throws RoleNotFoundException {
        RegisterResponseDTO profile = userService.registerUser(data);
        return ResponseEntity.ok(profile);
    }
}

