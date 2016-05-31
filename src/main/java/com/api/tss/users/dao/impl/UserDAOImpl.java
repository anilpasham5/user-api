package com.api.tss.users.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.tss.users.dao.UserDAO;
import com.api.tss.users.models.Users;
import com.api.tss.users.repo.UserRepository;
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	UserRepository repo;
	
	
	public boolean saveUser(Users user) {
		
		return false;
		
	}
	
	public List<Users> getAllEmployees(){
		
		return repo.findAll();
		
	}

	@Override
	public Users getUser(String id) {
		
		return repo.findOne(id);
		
	}

	@Override
	public String deleteUser(String id) {
		// TODO Auto-generated method stub
		 try{
			 repo.delete(id);
			 return "DELETED";
		 }catch(Exception e){ return "NOT DELETED";}
		
	}

}
