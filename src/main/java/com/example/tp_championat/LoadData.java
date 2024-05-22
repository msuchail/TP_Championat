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
    private UserRepository userRepository;

    @Autowired
    private ChampionshipRepository championshipRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private GameRepository gameRepository;




    // Ajout des utilisateurs dans la base de données
    @Bean
    CommandLineRunner initData() throws ParseException {
        if (userRepository.count() == 0) {
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
            userRepository.saveAll(users);

            Championship ligue1 = new Championship(
                    "Ligue 1",
                    creationDate,
                    creationDate,
                    3,
                    1,
                    0
            );
            Championship ligue2 = new Championship(
                    "Ligue 2",
                    creationDate,
                    creationDate,
                    3,
                    1,
                    0
            );

            Team psg = new Team(
                    "PSG",
                    creationDate
            );
            Team om = new Team(
                    "OM",
                    creationDate
            );
            Team ol = new Team(
                    "OL",
                    creationDate
            );
            Team asse = new Team(
                    "ASSE",
                    creationDate
            );

            teamRepository.save(psg);
            teamRepository.save(om);
            teamRepository.save(ol);
            teamRepository.save(asse);


            ligue1.addTeam(psg);
            ligue1.addTeam(om);
            ligue2.addTeam(ol);
            ligue2.addTeam(asse);


            Day day1 = new Day(1);
            dayRepository.save(day1);

            Game game1 = new Game(1, 1, psg, om, day1);
            gameRepository.save(game1);

            day1.addGame(game1);



            ligue1.addDay(day1);

            championshipRepository.save(ligue1);
            championshipRepository.save(ligue2);
        }
        return args -> {
            log.info("Nombre d'utilisateurs: " + userRepository.count());
        };
    }
}