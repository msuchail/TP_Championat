package com.example.tp_championat.controllers;

import com.example.tp_championat.models.User;
import com.example.tp_championat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

//    Récupérer la liste des utilisateurs
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<>(userRepository.findAll());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    Récupérer un utilisateur par son id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    Récupérer un utilisateur par son email et son mot de passe
    @PostMapping("/")
    public ResponseEntity<User> getUserByEmailAndPassword(@RequestBody User user, BindingResult bindingResult) {
        User result = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).orElse(null);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    Ajouter un utilisateur
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        user.setCreationDate(LocalDate.now());
        User result = userRepository.save(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

//    Modifier un utilisateur
    @PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userToUpdate = userRepository.findById(user.getId()).orElse(null);
        if(userToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User result = userRepository.save(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
