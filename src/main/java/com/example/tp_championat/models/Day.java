package com.example.tp_championat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
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

    @ManyToOne()
    @JoinColumn(name = "championship_id")
    @JsonIgnore
    private Championship championship;

    @OneToMany(mappedBy = "day")
    @JsonIgnore
    private List<Game> games;



    public Day() {
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public Day(int number) {
        this.number = number;
        this.games = new ArrayList<>();
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> matches) {
        this.games = matches;
    }
}
