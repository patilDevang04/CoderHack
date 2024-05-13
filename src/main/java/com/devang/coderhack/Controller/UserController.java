package com.devang.coderhack.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devang.coderhack.Entities.ScoreUpdateRequest;
import com.devang.coderhack.Entities.User;
import com.devang.coderhack.UserService.UserService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
        * Retrieves a list of all users.
        *
        * @return ResponseEntity<List<String>> containing the list of users.
    */
    @GetMapping
    public ResponseEntity<List<String>> getAllUsers() {
        List<String> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
        * Retrieves a user by their ID.
        *
        * @param userId the ID of the user to retrieve
        * @return the ResponseEntity containing the user if found, or a not found response if the user does not exist
    */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
        * Registers a new user.
        *
        * @param user the user object to be registered
        * @return the ResponseEntity containing the registered user
    */
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        
        if (user.getUserName() == null || user.getUserId() == null) {
            return ResponseEntity.badRequest().build();
        }
        
        userService.addNewUser(user.getUserId(), user.getUserName());
        return ResponseEntity.ok(user);
    }

    /**
        * Updates the score of a user with the specified user ID.
        *
        * @param userId The ID of the user to update.
        * @param score The new score for the user.
        * @return ResponseEntity<User> The response entity containing the updated user if found, or a not found response if the user does not exist.
    */
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserScore(@PathVariable Long userId, @RequestBody ScoreUpdateRequest score) {
        userService.updateUserScore(userId, score);
        User updatedUser = userService.getUser(userId);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
        * Deletes a user with the specified user ID.
        *
        * @param userId the ID of the user to be deleted
        * @return a ResponseEntity with the deleted user ID if found, or a not found response if the user does not exist
    */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> deregisterUser(@PathVariable Long userId) {
        userService.deregisterUser(userId);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}