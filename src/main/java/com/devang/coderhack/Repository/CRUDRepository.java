package com.devang.coderhack.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.devang.coderhack.Entities.User;




public interface CRUDRepository extends MongoRepository<User, Long> {
	
	List<User> findAll();

	Optional<User> findByUserId(Long id);

	void deleteByUserId(Long userId);

	User save(User user);

	void delete(User user);
}
