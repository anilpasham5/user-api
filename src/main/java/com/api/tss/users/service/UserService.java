package com.api.tss.users.service;

import java.util.List;

import com.api.tss.users.pojo.UserPojo;

public interface UserService {

	public boolean userRegistration(UserPojo user);
	List<UserPojo> getAllEmployees();
	UserPojo getUser(String id);
	String deleteUser(String id);
} 
