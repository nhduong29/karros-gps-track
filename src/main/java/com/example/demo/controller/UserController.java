package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.security.CurrentUser;
import com.example.demo.config.security.CustomUserDetail;
import com.example.demo.entity.User;
import com.example.demo.rest.response.UserIdentityAvailability;
import com.example.demo.rest.response.UserProfile;
import com.example.demo.rest.response.UserSummaryResponse;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserSummaryResponse getCurrentUser(@CurrentUser CustomUserDetail currentUser) {
		UserSummaryResponse userSummary = new UserSummaryResponse(currentUser.getId(), currentUser.getUsername(),
				currentUser.getName());
		return userSummary;
	}

	@GetMapping("/user/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = userService.checkUsernameAvailability(username);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/users/{username}")
	public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
		User user = userService.getUserProfile(username);
		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName());

		return userProfile;
	}
}
