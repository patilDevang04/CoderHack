package com.devang.coderhack.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devang.coderhack.Entities.ScoreUpdateRequest;
import com.devang.coderhack.Entities.User;
import com.devang.coderhack.Repository.UserRepositoryService;
import java.util.Optional; // Import the Optional class

/**
 * This class represents a service for managing user-related operations.
 * It provides methods to retrieve, add, update, and delete users.
 */
@Service
public class UserService {
    @Autowired
    private UserRepositoryService _userRepositoryService; 
    
    public List<String> getAllUsers() { 
        return _userRepositoryService.getAllUsernames();
    }
    
    public User getUser(Long userId) { 
        return _userRepositoryService.getUserDetails(userId);
    }
    public void addNewUser(Long userId, String username) { 
        _userRepositoryService.addUser(userId, username);
    }

    public User updateUserScore(Long userId, ScoreUpdateRequest request) {
        return _userRepositoryService.updateUserScore(userId, request.getScore());
    }

    
    public void deregisterUser(Long userId) {
        _userRepositoryService.deleteUser(userId);
    }
}