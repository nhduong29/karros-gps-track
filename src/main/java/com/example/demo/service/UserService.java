package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
	Boolean checkUsernameAvailability(String username);

	User getUserProfile(String username);

	boolean existsByUsername(String username);

	User save(User user);
}
