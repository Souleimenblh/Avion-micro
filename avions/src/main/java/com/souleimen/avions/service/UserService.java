package com.souleimen.avions.service;

import com.souleimen.avions.entities.Role;
import com.souleimen.avions.entities.User;

public interface UserService {
	void deleteAllusers();
	void deleteAllRoles();
	//User saveUser(User user);
	User findUserByUsername (String username);
	Role addRole(Role role);
	User addRoleToUser(String username, String rolename);
}