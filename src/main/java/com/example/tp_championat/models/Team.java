package com.example.tp_championat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message = "Le nom de l'équipe est obligatoire")
    private String name;

    @Column
    @NotNull(message = "La date de création est obligatoire")
    private LocalDate creationDate;

    @ManyToMany(mappedBy = "teams")
    @JsonIgnore
    private List<Championship> championships;

    public Team() {
    }

    public Team(String name, LocalDate creationDate) {
        this.name = name;
        this.creationDate = creationDate;
        this.championships = new ArrayList<Championship>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Championship> getChampionships() {
        return championships;
    }

    public void setChampionships(List<Championship> championships) {
        this.championships = championships;
    }
}
