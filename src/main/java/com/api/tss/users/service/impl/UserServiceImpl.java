package com.api.tss.users.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tss.users.dao.UserDAO;
import com.api.tss.users.models.Users;
import com.api.tss.users.pojo.UserPojo;
import com.api.tss.users.repo.UserRepository;
import com.api.tss.users.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;
	@Autowired
	UserRepository repo;
	
	public boolean userRegistration(UserPojo user) {
		Users u = new Users();
		u.setEmail(user.getEmail());
		u.setMobile(user.getMobile());
		u.setName(user.getName());
		Users u1 = repo.save(u);
		if(u1 != null){
			return true; 
		}
		/*if(userDao.saveUser(u)){
			return true;
		}*/
		
		return false;
	}
	
	public List<UserPojo> getAllEmployees(){
		List<Users> list=userDao.getAllEmployees();
		List<UserPojo> usersList=new ArrayList<UserPojo>();
		for(int i=0;i<list.size();i++){
			UserPojo user=new UserPojo();
			user.setEmail(list.get(i).getEmail());
			user.setId(list.get(i).getId());
			user.setMobile(list.get(i).getMobile());
			user.setName(list.get(i).getName());
			usersList.add(user);
		}
		return usersList;
	}

	@Override
	public UserPojo getUser(String id) {
		
		Users user=userDao.getUser(id);
		UserPojo userPojo=new UserPojo();
		userPojo.setEmail(user.getEmail());
		userPojo.setId(user.getId());
		userPojo.setMobile(user.getMobile());
		userPojo.setName(user.getName());
		return userPojo;
		
	}

	@Override
	public String deleteUser(String id) {
		
		return userDao.deleteUser(id);
	}

}
