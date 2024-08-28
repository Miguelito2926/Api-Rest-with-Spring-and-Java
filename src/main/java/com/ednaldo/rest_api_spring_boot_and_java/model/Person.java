package com.ednaldo.rest_api_spring_boot_and_java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pessoa")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 20, nullable = false)
    private String firstName;

    @Column(name = "sobre_nome", length = 30, nullable = false)
    private String lastName;

    @Column(name = "endereco", length = 100)
    private String address;

    @Column(name = "sexo", length = 1)
    private String gender;
}
