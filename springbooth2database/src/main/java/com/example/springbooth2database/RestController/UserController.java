package com.example.springbooth2database.RestController;


import com.example.springbooth2database.Entity.User;
import com.example.springbooth2database.Repository.UserRepository;
import com.example.springbooth2database.Repository.UserRepositoryCustom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserRepository userRepo;
    private final UserRepositoryCustom userRepoCustom;

    public UserController(UserRepository userRepo, UserRepositoryCustom userRepoCustom) {
        this.userRepo = userRepo;
        this.userRepoCustom = userRepoCustom;
    }


    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            Optional<User> user1= userRepoCustom.nativeUpdate(user);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }


}
