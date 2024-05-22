package com.example.tp_championat.repository;

import com.example.tp_championat.models.Championship;
import com.example.tp_championat.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findGamesByDayId(Long id);
}
