package org.krams.tutorial.service;

import java.util.List;

import org.krams.tutorial.domain.User;

public interface IUserService {

	/**
	 * Retrieves all users
	 * 
	 * @return list of users
	 */
	public List<User> getAll();

	/**
	 * Retrieves a single user based on id
	 * 
	 * @param id the id of the user
	 * @return the user
	 */
	public User get(String id);

	/**
	 * Add a new user
	 * 
	 * @param user the new user
	 * @return true if successful
	 */
	public Boolean add(User user);

	/**
	 * Delete an existing user
	 * 
	 * @param user the existing user
	 * @return true if successful
	 */
	public Boolean delete(User user);

	/**
	 * Edit an existing user
	 * 
	 * @param user the existing user
	 * @return true if successful
	 */
	public Boolean edit(User user);

}