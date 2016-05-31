package com.api.tss.users.dao;

import java.util.List;

import com.api.tss.users.models.Users;


public interface UserDAO {

	public boolean saveUser(Users user);
	List<Users> getAllEmployees();
	Users getUser(String id);
	String deleteUser(String id);
}
