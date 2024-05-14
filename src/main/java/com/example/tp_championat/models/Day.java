package com.example.tp_championat.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "days")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    @NotNull(message = "Le numéro de la journée est obligatoire")
    private int number;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;

    @OneToMany(mappedBy = "day")
    private List<Game> matches;

    public Day() {
    }

    public Day(int number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public List<Game> getMatches() {
        return matches;
    }

    public void setMatches(List<Game> matches) {
        this.matches = matches;
    }
}
