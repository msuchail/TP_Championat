package com.example.tp_championat.models;

import com.example.tp_championat.repository.DayRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "championships")
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Le nom du championnat est obligatoire")
    private String name;

    @Column(name = "start_date")
    @NotNull(message = "La date de début est obligatoire")
    private LocalDate startDate;

    @Column(name = "end_date")
    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate endDate;

    @Column(name = "won_points")
    @NotNull(message = "Le nombre de points pour une victoire est obligatoire")
    private int wonPoints;

    @Column(name = "draw_points")
    @NotNull(message = "Le nombre de points pour un match nul est obligatoire")
    private int drawPoints;

    @Column(name = "lost_points")
    @NotNull(message = "Le nombre de points pour une défaite est obligatoire")
    private int lostPoints;

    @ManyToMany
    @JsonIgnore
    private List<Team> teams;

    @OneToMany
    @JoinColumn(name = "championship_id")
    @JsonIgnore
    private List<Day> days;

    public Championship() {
    }

    public Championship(String name, LocalDate startDate, LocalDate endDate, int wonPoints, int drawPoints, int lostPoints) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wonPoints = wonPoints;
        this.drawPoints = drawPoints;
        this.lostPoints = lostPoints;
        this.teams = new ArrayList<>();
        this.days = new ArrayList<>();
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }
    public void addDay(Day day) {
        this.days.add(day);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getWonPoints() {
        return wonPoints;
    }

    public void setWonPoints(int wonPoints) {
        this.wonPoints = wonPoints;
    }

    public int getDrawPoints() {
        return drawPoints;
    }

    public void setDrawPoints(int drawPoints) {
        this.drawPoints = drawPoints;
    }

    public int getLostPoints() {
        return lostPoints;
    }

    public void setLostPoints(int lostPoints) {
        this.lostPoints = lostPoints;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
