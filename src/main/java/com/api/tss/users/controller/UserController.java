package com.api.tss.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.tss.users.pojo.UserPojo;
import com.api.tss.users.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public  String userResgistration(@ModelAttribute("user") UserPojo user) {
		/*Properties properties=new Properties();
		System.out.println("ENV:: "+properties.getProperty("ENV"));
		System.out.println("ENV2:: "+System.getenv("ENV"));
		System.out.println("ENV3:: "+System.getProperty("ENV"));
		System.out.println("ENV4:: "+TssUtils.getProperty("NAME"));*/
		UserPojo user1 = new UserPojo();
		user1.setEmail(user.getEmail());
		user1.setName(user.getName());
		user1.setMobile(user.getMobile());
		System.out.println("name: " + user1.getName());
		if (user.getEmail() == null) {
			try {
				throw new Exception("Email is required");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (userService.userRegistration(user1)) {
			return "Inserted====";
		}

		return "Something worng pls check......";
	}
	
	@RequestMapping(value="/getallusers")
	public List<UserPojo> getAllUsers(){
		
		return userService.getAllEmployees();
		
	}
	
	@RequestMapping(value="/getuser",method=RequestMethod.POST)
	public UserPojo getUser(@RequestParam("id") String id){
		
		return userService.getUser(id);
		
	}
	@RequestMapping(value="/deleteuser",method=RequestMethod.POST)
	public String deleteUser(@RequestParam("id") String id){
		return userService.deleteUser(id);
	}
	
	
}
