package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.enums.RoleEnum;

public interface RoleService {
	Role findByName(RoleEnum roleName);
}
