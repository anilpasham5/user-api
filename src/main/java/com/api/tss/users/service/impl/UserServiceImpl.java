package com.api.tss.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.tss.users.dao.UserDAO;
import com.api.tss.users.models.UserEntity;
import com.api.tss.users.pojo.UserPojo;
import com.api.tss.users.service.UserService;
import com.api.tss.utility.Generator;
@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
	private UserDAO userDAO;
	
    @Override
	public String addUser(UserPojo userPojo) {
		UserEntity userEntity=new UserEntity();
		userEntity.setUserEmailId(userPojo.getUserEmailId());
		userEntity.setUserContactNumber(userPojo.getUserContactNumber());
		userEntity.setUserFirstName(userPojo.getUserFirstName());
		userEntity.setUserLastName(userPojo.getUserLastName());
		userEntity.setUserPassword(userPojo.getUserPassword());
		userEntity.setUserStatus("INACTIVE");
		userEntity.setUserToken(Generator.generateToken());
		System.out.println(userEntity);
		String string= userDAO.addUser(userEntity);
		if(string.equalsIgnoreCase("YES")) {
		String mailSubject="Activate your  account";
        String mailText="Click on this link to activate your account "
		+"http://localhost:2011/user-apifromgit/activateuser?userEmailId="+userEntity.getUserEmailId()
		+"&userToken="+userEntity.getUserToken();
        Generator.sendEmail(userEntity.getUserEmailId(),mailSubject,mailText);
		}
		return string;
	}
    
	@Override
	public boolean isUserEmailIdExists(String userEmailId) {
	   return userDAO.isUserEmailIdexists(userEmailId);
	}
	
	@Override
	public String verifyUser(String userEmailId, String userPassword) {
	   return userDAO.verifyUser(userEmailId, userPassword);
	}
	
	@Override
	public String activateUser(UserPojo userPojo) {
		UserEntity userEntity=new UserEntity();
		userEntity.setUserEmailId(userPojo.getUserEmailId());
		userEntity.setUserToken(userPojo.getUserToken());
	    return userDAO.activateUser(userEntity);
	}
	
	@Override
	public String checkMatch(String userEmailId, long userContactNumber) {
        return userDAO.checkMatch(userEmailId, userContactNumber);
	}
	
	@Override
	public String forgotPassword(UserPojo userPojo) {
		UserEntity userEntity=new UserEntity();
		userEntity.setUserToken(Generator.generateToken());
		userEntity.setUserContactNumber(userPojo.getUserContactNumber());
		userEntity.setUserEmailId(userPojo.getUserEmailId());
		String string=userDAO.forgotPassword(userEntity);
		if(string.equalsIgnoreCase("YES")) {
		String mailSubject="Reset Password";
	    String mailText="Click here to reset your password "
		+"http://localhost:2011/user-apifromgit/verifyfplink?userEmailId="
	    +userEntity.getUserEmailId()+"&userToken="+userEntity.getUserToken();
		Generator.sendEmail(userEntity.getUserEmailId(),mailSubject,mailText);
		return "YES";
		}
		return string;
	}
	
	@Override
	public String verifyForgotPasswordLink(String userEmailId, String userToken) {
		return userDAO.verifyForgotPasswordLink(userEmailId, userToken);
	}
	
	@Override
	public String resetPassword(String userEmailId, String userPassword) {
		return userDAO.resetPassword(userEmailId, userPassword);
	}
	
	@Override
	public UserPojo getUserProfile(String userEmailId) {
		UserEntity userEntity=userDAO.getUserProfile(userEmailId);
		UserPojo userPojo=new UserPojo();
		userPojo.setUserContactNumber(userEntity.getUserContactNumber());
		userPojo.setUserEmailId(userEmailId);
		userPojo.setUserFirstName(userEntity.getUserFirstName());
		userPojo.setUserLastName(userEntity.getUserLastName());
		return userPojo;
	}
	
	@Override
	public String updateUser(UserPojo userPojo) {
		UserEntity userEntity=new UserEntity();
		userEntity.setUserContactNumber(userPojo.getUserContactNumber());
		userEntity.setUserEmailId(userPojo.getUserEmailId());
		userEntity.setUserFirstName(userPojo.getUserFirstName());
		userEntity.setUserLastName(userPojo.getUserLastName());
		return userDAO.updateUser(userEntity);
	}
	
	@Override
	public String changePassword(String userEmailId, String userCurrentPassword, String userNewPassword) {
		return userDAO.changePassword(userEmailId, userCurrentPassword, userNewPassword);
	}

	@Override
	public String uploadImage(String userEmailId, MultipartFile file) {
		// TODO Auto-generated method stub
		return userDAO.uploadImage(userEmailId, file);
	}

	@Override
	public String getImage(String userEmailId) {
		// TODO Auto-generated method stub
		return userDAO.getImage(userEmailId);
	}

}
