package com.ednaldo.rest_api_spring_boot_and_java.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@JsonPropertyOrder({"id", "firstName", "lastName", "email", "phone", "address", "gender", "birthDate", "createdAt", "updatedAt"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("nome")
    private String firstName;

    @JsonProperty("sobrenome")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("telefone")
    private String phone;

    @JsonProperty("endereco")
    private String address;

    @JsonProperty("sexo")
    private String gender;

    @JsonProperty("ativo")
    private Boolean enabled;

    @JsonProperty("data_nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime birthDate;

    @JsonProperty("data_criacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("data_atualizacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
