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

    
    @GetMapping
    public ResponseEntity<List<String>> getAllUsers() {
        List<String> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        
        if (user.getUserName() == null || user.getUserId() == null) {
            return ResponseEntity.badRequest().build();
        }
        
        userService.addNewUser(user.getUserId(), user.getUserName());
        return ResponseEntity.ok(user);
    }

    
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