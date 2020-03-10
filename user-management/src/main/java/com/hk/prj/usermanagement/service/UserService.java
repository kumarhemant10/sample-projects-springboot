package com.hk.prj.usermanagement.service;

import java.util.List;

import com.hk.prj.usermanagement.model.User;

import javassist.NotFoundException;

public interface UserService {

	/**
	 * 
	 * @return - all users 
	 */
	List<User> findAll();

	/**
	 * persist user in database
	 * 
	 * @param user
	 */
	User save(User user);

	/**
	 * get user by userId
	 * 
	 * @param userId
	 * @return
	 * @throws NotFoundException 
	 */
	User findById(Long userId);

	/**
	 * delete user with userId
	 * 
	 * @param userId
	 */
	void delete(Long userId);

}
