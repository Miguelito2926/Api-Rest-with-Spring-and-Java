package com.ednaldo.rest_api_spring_boot_and_java.config.security;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PublicEndpoints {

    public List<String> getEndpoints() {
        return Arrays.asList(
                "/users/**",
                "/auth/**",
                "/swagger-ui/**",
                "/v3/api-docs/**"
                // Adicione mais endpoints aqui
        );
    }
}