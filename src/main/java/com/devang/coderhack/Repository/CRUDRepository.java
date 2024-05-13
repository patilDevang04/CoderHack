package com.devang.coderhack.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.devang.coderhack.Entities.User;



/**
 * The CRUDRepository interface provides methods for performing CRUD operations on User objects.
 */
public interface CRUDRepository extends MongoRepository<User, Long> {
	/**
	 * Retrieves all users from the repository.
	 *
	 * @return a list of all users
	 */
	List<User> findAll();

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param id the ID of the user to retrieve
	 * @return an Optional containing the user, or an empty Optional if no user is found
	 */
	Optional<User> findByUserId(Long id);

	/**
	 * Deletes a user by their ID.
	 *
	 * @param userId the ID of the user to delete
	 */
	void deleteByUserId(Long userId);

	/**
	 * Saves a user to the repository.
	 *
	 * @param user the user to save
	 * @return the saved user
	 */
	User save(User user);

	/**
	 * Deletes a user from the repository.
	 *
	 * @param user the user to delete
	 */
	void delete(User user);
}
