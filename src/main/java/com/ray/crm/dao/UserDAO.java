package com.ray.crm.dao;

import com.ray.crm.entity.User;

public interface UserDAO {
	User findByUserName(String username);
	Object save(User user);
}
