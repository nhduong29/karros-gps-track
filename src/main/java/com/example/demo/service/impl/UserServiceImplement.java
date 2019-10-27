package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.rest.exception.GeneralException;
import com.example.demo.service.UserService;

@Service
public class UserServiceImplement implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Boolean checkUsernameAvailability(String username) {
		return !userRepository.existsByUsername(username);
	}

	@Override
	public User getUserProfile(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new GeneralException("User with the username : " + username + " is not found"));
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
