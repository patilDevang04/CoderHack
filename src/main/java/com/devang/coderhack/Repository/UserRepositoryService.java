package com.devang.coderhack.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.devang.coderhack.Entities.User;


@Service
public class UserRepositoryService {
	
	@Autowired
	private CRUDRepository userRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate; 
	
	
	/**
     * Retrieves a list of all usernames from the database, sorted by score.
     *
     * @return a list of usernames
     */
    public List<String> getAllUsernames() {
        Query query = new Query();
        query.fields().include("userName");
        query.with(Sort.by(Sort.Direction.DESC, "score"));
        List<String> usernames = mongoTemplate.find(query, User.class)
                .stream()
                .map(User::getUserName)
                .collect(Collectors.toList());
        return usernames;
    }

    /**
     * Retrieves the user details for the given user ID.
     *
     * @param userId the ID of the user to retrieve details for
     * @return the User object containing the user details, or null if no user is found with the given ID
     */
    public User getUserDetails(Long userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }

	/**
     * Deletes the user with the given ID.
     *
     * @param userId the ID of the user to delete
     */
    public void deleteUser(Long userId) {
        userRepository.deleteByUserId(userId);
    }

    /**
     * Adds a new user with the given username.
     *
     * @param username the username of the user to add
     * @return the added User object
     */
    public User addUser(Long userId , String username) {
        // Generate a unique user ID
        
        User user = new User();
        user.setUserId(userId);
        user.setUserName(username);
        return userRepository.save(user);
    }
    /**
     * Updates the score of the user with the given ID.
     *
     * @param userId the ID of the user to update
     * @param score the new score
     * @return the updated User object, or null if no user is found with the given ID
     */
    public User updateUserScore(Long userId, Long score) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setScore(score);
            userRepository.save(user);
            return user;
        } else {
            return null;
        }
    }

	


	
		
		
	


	
	
	
	
}
