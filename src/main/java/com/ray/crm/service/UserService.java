package com.ray.crm.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ray.crm.dto.RegisteredUser;

public interface UserService extends UserDetailsService {
	Object save(RegisteredUser registeredUser);
}
