package com.example.tp_championat.repository;

import com.example.tp_championat.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
