package com.example.tp_championat.repository;

import com.example.tp_championat.models.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipRepository extends JpaRepository<Championship, Long> {
}
