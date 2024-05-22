package com.example.tp_championat.repository;

import com.example.tp_championat.models.Championship;
import com.example.tp_championat.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findTeamsByChampionshipsId(Long id);
}
