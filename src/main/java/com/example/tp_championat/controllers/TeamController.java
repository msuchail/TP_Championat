package com.example.tp_championat.controllers;

import com.example.tp_championat.models.Team;
import com.example.tp_championat.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if(team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team result = teamRepository.save(team);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        Team teamToUpdate = teamRepository.findById(team.getId()).orElse(null);
        if(teamToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Team result = teamRepository.save(team);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if(team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        teamRepository.delete(team);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
