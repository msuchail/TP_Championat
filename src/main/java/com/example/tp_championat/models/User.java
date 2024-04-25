package com.example.tp_championat.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotNull(message = "Le prénon est obligatoire")
    @NotBlank(message = "Le prénon ne doit pas être vide")
    private String firstName;

    @NotNull(message = "Le nom est obligatoire")
    @NotBlank(message = "Le nom ne doit pas être vide")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "L'email est obligatoire")
    @NotBlank(message = "L'email ne doit pas être vide")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Le mot de passe est obligatoire")
    @NotBlank(message = "Le mot de passe ne doit pas être vide")
    @Column(name = "password")
    private String password;

    @Column(name = "creation_date")
    @NotNull(message = "La date de création est obligatoire")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;


    public User() {
    }
    public User(String firstName, String lastName, String email, String password, LocalDate creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
