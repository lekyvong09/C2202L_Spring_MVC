package com.ray.crm.dao;

import com.ray.crm.entity.Role;

public interface RoleDAO {
	Role findRoleByName(String roleName);
}
