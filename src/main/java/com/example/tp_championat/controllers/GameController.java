package com.example.tp_championat.controllers;

import com.example.tp_championat.models.Day;
import com.example.tp_championat.models.Game;
import com.example.tp_championat.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @GetMapping("/")
    public ResponseEntity<List<Game>> getGames() {
        List<Game> games = new ArrayList<>(gameRepository.findAll());
        return ResponseEntity.ok(games);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        if(game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }
    @GetMapping("/day/{id}")
    public ResponseEntity<List<Game>> getGamesByDay(@PathVariable Long id) {
        List<Game> games = new ArrayList<>(gameRepository.findGamesByDayId(id));
        return ResponseEntity.ok(games);
    }
    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        Game result = gameRepository.save(game);
        return ResponseEntity.ok(result);
    }
    @PutMapping("/")
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        Game result = gameRepository.findById(game.getId()).orElse(null);
        if(result == null) {
            return ResponseEntity.notFound().build();
        }
        gameRepository.save(game);
        return ResponseEntity.ok(game);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        if(game == null) {
            return ResponseEntity.notFound().build();
        }
        gameRepository.delete(game);
        return ResponseEntity.ok().build();
    }

}
