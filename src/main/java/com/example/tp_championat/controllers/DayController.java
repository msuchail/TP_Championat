package com.example.tp_championat.controllers;

import com.example.tp_championat.models.Day;
import com.example.tp_championat.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/days")
public class DayController {
    @Autowired
    private DayRepository dayRepository;

    @GetMapping("/")
    public ResponseEntity<List<Day>> getAllDays() {
        List<Day> days = new ArrayList<>(dayRepository.findAll());
        return ResponseEntity.ok(days);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Day> getDayById(@PathVariable Long id) {
        Day day = dayRepository.findById(id).orElse(null);
        if(day == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(day);
    }

    @PostMapping("/add")
    public ResponseEntity<Day> addDay(@RequestBody Day day) {
        Day result = dayRepository.save(day);
        return ResponseEntity.ok(result);
    }
    @PutMapping("/")
    public ResponseEntity<Day> updateDay(@RequestBody Day day) {
        Day result = dayRepository.findById(day.getId()).orElse(null);
        if(result == null) {
            return ResponseEntity.notFound().build();
        }
        dayRepository.save(day);
        return ResponseEntity.ok(day);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDay(@PathVariable Long id) {
        Day day = dayRepository.findById(id).orElse(null);
        if(day == null) {
            return ResponseEntity.notFound().build();
        }
        dayRepository.delete(day);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
