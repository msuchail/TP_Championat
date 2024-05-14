package com.example.tp_championat;

import com.example.tp_championat.models.*;
import com.example.tp_championat.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadData {
    private final Logger log = LoggerFactory.getLogger(LoadData.class);


    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DayRepository dayRepository;

    @Autowired ChampionshipRepository championshipRepository;


    // Ajout des utilisateurs dans la base de données
    @Bean
    CommandLineRunner initUsers(UserRepository repository) throws ParseException {
        if(repository.count() == 0) {
            LocalDate creationDate = LocalDate.now();
            List<User> users = new ArrayList<>();

            users.add(new User(
                    "John",
                    "Doe",
                    "john.doe@gmail.com",
                    "jodoe",
                    creationDate
            ));
            users.add(new User(
                    "Jane",
                    "Doe",
                    "jane.doe@gmail.com",
                    "jadoe",
                    creationDate
            ));
            users.add(new User(
                    "Alice",
                    "Doe",
                    "alice.doe@gmail.com",
                    "adoe",
                    creationDate
            ));


            return args -> {
                for(User user : users) {
                    log.info("Ajout de " + repository.save(user));
                }
            };
        } else {
            return args -> {
                log.info("Données déjà chargée");
            };
        }
    }

    @Bean
    CommandLineRunner initTeams(TeamRepository repository) throws ParseException {
        if (repository.count() == 0) {
            LocalDate creationDate = LocalDate.now();
            List<Team> teams = new ArrayList<>();

            teams.add(new Team(
                    "FC Barcelona",
                    creationDate
            ));
            teams.add(new Team(
                    "Real Madrid",
                    creationDate
            ));
            teams.add(new Team(
                    "Paris Saint-Germain",
                    creationDate
            ));

            return args -> {
                for (Team team : teams) {
                    log.info("Ajout de " + repository.save(team));
                }
            };
        } else {
            return args -> {
                log.info("Données déjà chargée");
            };
        }
    }

    @Bean
    CommandLineRunner initChampionships(ChampionshipRepository repository) throws ParseException {
        if (repository.count() == 0) {
            LocalDate creationDate = LocalDate.now();
            List<com.example.tp_championat.models.Championship> championships = new ArrayList<>();

            Championship test = new Championship(
                    "Ligue 1",
                    LocalDate.of(2021, 8, 6),
                    LocalDate.of(2022, 5, 23),
                    3,
                    1,
                    0,
                    teamRepository.findAll()
                );

            championships.add(new Championship(
                    "Ligue 1",
                    LocalDate.of(2021, 8, 6),
                    LocalDate.of(2022, 5, 23),
                    3,
                    1,
                    0,
                    teamRepository.findAll()
                )
            );
            championships.add(new Championship(
                    "Premier League",
                    LocalDate.of(2021, 8, 13),
                    LocalDate.of(2022, 5, 23),
                    3,
                    1,
                    0,
                    teamRepository.findAll()
                )
            );
            return args -> {
                for (Championship championship : championships) {
                    log.info("Ajout de " + repository.save(championship));
                }
            };
        } else {
            return args -> {
                log.info("Données déjà chargée");
            };
        }
    }

    @Bean
    CommandLineRunner initDays(DayRepository repository) throws ParseException {
        if (repository.count() == 0) {
            LocalDate creationDate = LocalDate.now();
            List<Day> days = new ArrayList<>();
            days.add(new Day(
                    1
                )
            );
            days.add(new Day(
                    2
                )
            );
            return args -> {
                for (Day day : days) {
                    log.info("Ajout de " + repository.save(day));
                }
            };
        } else {
            return args -> {
                log.info("Données déjà chargée");
            };
        }
    }
}
