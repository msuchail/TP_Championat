package com.example.tp_championat.controllers;

import com.example.tp_championat.models.Championship;
import com.example.tp_championat.models.Team;
import com.example.tp_championat.repository.ChampionshipRepository;
import com.example.tp_championat.repository.TeamRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ChampionshipRepository championshipRepository;

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
    @GetMapping("/get-by-championship-id/{id}")
    public ResponseEntity<List<Team>> getTeamsByChampionship(@PathVariable Long id) {
        List<Team> teams = teamRepository.findTeamsByChampionshipsId(id);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team result = teamRepository.save(team);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PutMapping("/add-to-championship")
    public ResponseEntity<Championship> addTeamToChampionship(@RequestBody ObjectNode objectNode) {
        Team team = teamRepository.findById(objectNode.get("teamId").asLong()).orElse(null);
        Championship championship = championshipRepository.findById(objectNode.get("championshipId").asLong()).orElse(null);

        if(team == null || championship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        championship.addTeam(team);
        championshipRepository.save(championship);
        return new ResponseEntity<>(championship, HttpStatus.OK);
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
