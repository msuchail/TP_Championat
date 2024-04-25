package com.example.tp_championat;

import com.example.tp_championat.models.User;
import com.example.tp_championat.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) throws ParseException {
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
}
