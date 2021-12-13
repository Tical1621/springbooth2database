package com.example.springbooth2database.controller.rest;


import com.example.springbooth2database.entity.User;
import com.example.springbooth2database.repository.UserRepository;
import com.example.springbooth2database.repository.UserRepositoryCustom;
import com.example.springbooth2database.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserRepository userRepo;
    private final UserRepositoryCustom userRepoCustom;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("exception with post request", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> list = userRepo.findAll();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            log.error("exception with get request", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        try {
            Optional<User> user = userRepo.findById(id);
            user.ifPresent(userRepo::delete);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("exception with delete request", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UserDto user) {
//        try {
            return userService.save(user);
//            return ResponseEntity.ok(savedUser);

//        } catch (Exception e) {
//            log.error("exception with update request {}", e.getMessage());
//            throw  new IllegalArgumentException();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR );
//        }
    }
}
