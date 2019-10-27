package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.config.security.CustomUserDetail;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Can not found User with username : " + username));
		return CustomUserDetail.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Can not found User with id : " + id));

		return CustomUserDetail.create(user);
	}
}
