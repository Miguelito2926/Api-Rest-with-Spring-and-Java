package com.ednaldo.rest_api_spring_boot_and_java.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonPropertyOrder({"address","firstName","lastName","gender"})
public class PersonDTO  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private Long id;

    @JsonProperty(value = "nome")
    private String firstName;

    @JsonProperty(value = "sobre_nome")
    private String lastName;

    @JsonProperty(value = "endereço")
    private String address;

    @JsonProperty(value = "sexo")
    private String gender;
}

