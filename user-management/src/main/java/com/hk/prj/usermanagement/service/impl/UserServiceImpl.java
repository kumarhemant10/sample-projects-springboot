package com.hk.prj.usermanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.prj.usermanagement.exception.ResourceNotFoundException;
import com.hk.prj.usermanagement.model.User;
import com.hk.prj.usermanagement.repository.UserRepository;
import com.hk.prj.usermanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired UserRepository userRepo;
	
	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public void save(User user) {
		userRepo.save(user);
	}

	@Override
	public User findById(Long userId) {
		return userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user not found with userId: "+userId));
	}

	@Override
	public void delete(Long userId) {
		User user = findById(userId);
		userRepo.delete(user);
	}

}
