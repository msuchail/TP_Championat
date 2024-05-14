package com.example.tp_championat.controllers;

import com.example.tp_championat.models.Championship;
import com.example.tp_championat.repository.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/championships")
public class ChampionshipController {
    @Autowired
    private ChampionshipRepository championshipRepository;

    @GetMapping("/")
    public ResponseEntity<List<Championship>> getAllChampionships() {
        List<Championship> championships = new ArrayList<>(championshipRepository.findAll());
        return new ResponseEntity<>(championships, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Championship> getChampionshipById(@PathVariable Long id) {
        Championship championship = championshipRepository.findById(id).orElse(null);
        if(championship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(championship, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Championship> addChampionship(@RequestBody Championship championship) {
        Championship result = championshipRepository.save(championship);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Championship> updateChampionship(@RequestBody Championship championship) {
        Championship championshipToUpdate = championshipRepository.findById(championship.getId()).orElse(null);
        if(championshipToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Championship result = championshipRepository.save(championship);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteChampionship(@PathVariable Long id) {
        Championship championship = championshipRepository.findById(id).orElse(null);
        if(championship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        championshipRepository.delete(championship);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
