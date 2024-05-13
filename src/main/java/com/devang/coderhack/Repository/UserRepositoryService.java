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

    
    public User getUserDetails(Long userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }

	
    public void deleteUser(Long userId) {
        userRepository.deleteByUserId(userId);
    }

    
    public User addUser(Long userId , String username) {
        // Generate a unique user ID
        
        User user = new User();
        user.setUserId(userId);
        user.setUserName(username);
        return userRepository.save(user);
    }
    
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
