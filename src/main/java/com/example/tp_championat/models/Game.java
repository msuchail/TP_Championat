package com.example.tp_championat.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "team1_points")
    @NotNull(message = "Le nombre de points de l'équipe 1 est obligatoire")
    private int team1Points;

    @Column(name = "team2_points")
    @NotNull(message = "Le nombre de points de l'équipe 2 est obligatoire")
    private int team2Points;

    @ManyToOne
    @JoinColumn(name = "team1_id", referencedColumnName = "id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", referencedColumnName = "id")
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "day_id", referencedColumnName = "id")
    private Day day;

    public Game() {
    }

    public Game(int team1Points, int team2Points, Team team1, Team team2, Day day) {
        this.team1Points = team1Points;
        this.team2Points = team2Points;
        this.team1 = team1;
        this.team2 = team2;
        this.day = day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTeam1Points() {
        return team1Points;
    }

    public void setTeam1Points(int team1Points) {
        this.team1Points = team1Points;
    }

    public int getTeam2Points() {
        return team2Points;
    }

    public void setTeam2Points(int team2Points) {
        this.team2Points = team2Points;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
