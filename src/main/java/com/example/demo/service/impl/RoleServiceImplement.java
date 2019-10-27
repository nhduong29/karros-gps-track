package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.enums.RoleEnum;
import com.example.demo.repo.RoleRepository;
import com.example.demo.rest.exception.GeneralException;
import com.example.demo.service.RoleService;

@Service
public class RoleServiceImplement implements RoleService {

	@Autowired
	RoleRepository roleRepo;

	@Override
	public Role findByName(RoleEnum roleName) {
		return roleRepo.findByName(RoleEnum.ROLE_USER).orElseThrow(() -> new GeneralException("User Role not set."));
	}

}
