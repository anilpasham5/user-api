package com.api.tss.users.dao.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.api.tss.users.dao.UserDAO;
import com.api.tss.users.models.UserEntity;
import com.api.tss.users.repo.UserRepository;
@Repository
public class UserDAOImpl implements UserDAO {
	
    @Autowired
	private UserRepository userRepository;
    
	@Override
	public String addUser(UserEntity userEntity) {
		UserEntity userEntity2=userRepository.save(userEntity);
		if(userEntity2!=null) return "YES"; 
		return "NO";
	}
	
	@Override
	public boolean isUserEmailIdexists(String userEmailId) {
		return userRepository.exists(userEmailId);
	}
	
	@Override
	public String verifyUser(String userEmailId, String userPassword) {
		UserEntity userEntity=userRepository.findOne(userEmailId);
		System.out.println(userEntity);
		if(userEntity==null) return "EmailId does not exists Please register";
		else{
			if(userEntity.getUserStatus().equalsIgnoreCase("INACTIVE")) 
				return "Please Activate Your Account";
			else if(!userEntity.getUserPassword().equals(userPassword)) 
				   return "Wrong Password Please try again";
			     else return "YES";
		}
	}
	
	@Override
	public String activateUser(UserEntity userEntity) {
		UserEntity userEntity2=userRepository.findOne(userEntity.getUserEmailId());
		if(userEntity2==null) return "Invalid URL Please Check";
		else{
		if(userEntity2.getUserToken().equalsIgnoreCase(userEntity.getUserToken())){
			userEntity2.setUserStatus("ACTIVE");
			if(userRepository.save(userEntity2)!=null)
			return "Your account activated successfully please login";
			else return "Something Error Occured Please Try again";
		}
		else return "Invalid URL Please try again";
		}
	}
	
	@Override
	public String checkMatch(String userEmailId, long userContactNumber) {
		
		UserEntity userEntity=userRepository.findOne(userEmailId);
		if(userEntity==null) return "EmailId doesn't exists Please "
				+ "<a href='userregistration.jsp'> Register</a>";
		else if(userEntity.getUserContactNumber()!=userContactNumber) 
			return "EmailId doesn't match with mobile number Please check";
		     else return "";
	}
	
	@Override
	public String forgotPassword(UserEntity userEntity) {
		UserEntity userEntity3=userRepository.findOne(userEntity.getUserEmailId());
		if(userEntity3==null) return "EmailId doesn't exists Please "
				+ "<a href='userregistration.jsp'>Register</a>";
		else userEntity3.setUserToken(userEntity.getUserToken());
		UserEntity userEntity2=userRepository.save(userEntity3);
	    if(userEntity2==null) return "FAILED";
		else return "YES";
	}
	
	@Override
	public String verifyForgotPasswordLink(String userEmailId, String userToken) {
		UserEntity userEntity=userRepository.findOne(userEmailId);
		if(userEntity==null) return "Invalid URL please try again";
		else if(userEntity.getUserToken().equals(userToken)) return "YES";
		else return "Invalid URL please try again";
	}
	
	@Override
	public String resetPassword(String userEmailId, String userPassword) {
		UserEntity userEntity=userRepository.findOne(userEmailId);
		if(userEntity==null) return "Invalid EmailId Please Try again";
		else {
			userEntity.setUserPassword(userPassword);
			if(userRepository.save(userEntity)==null) return "Something Error occured Please try again";
			else return "Your Password Successfully changed Please Login";
		}
		
	}
	
	@Override
	public UserEntity getUserProfile(String userEmailId) {
		return userRepository.findOne(userEmailId);
	}
	
	@Override
	public String updateUser(UserEntity userEntity) {
		UserEntity userEntity2=userRepository.findOne(userEntity.getUserEmailId());
		if(userEntity2!=null)
		{
	       userEntity2.setUserContactNumber(userEntity.getUserContactNumber());
	       userEntity2.setUserFirstName(userEntity.getUserFirstName());
	       userEntity2.setUserLastName(userEntity.getUserLastName());
	       UserEntity userEntity3=userRepository.save(userEntity2);
	       if(userEntity3!=null) return "Your Profile Updated";
	       else return "Failed Try Again";
		}
		return "Invalid EmailId Please Try Again";
	}
	
	@Override
	public String changePassword(String userEmailId,String userCurrentPassword,String userNewPassword) {
		UserEntity userEntity=userRepository.findOne(userEmailId);
		if(userEntity==null) return "Invalid EmailId Please login again";
		else if(userEntity.getUserPassword().equals(userCurrentPassword)){
			userEntity.setUserPassword(userNewPassword);
			userEntity=userRepository.save(userEntity);
			if(userEntity==null) return "Failed to change the password try again";
			else return "YES";
		}
		else return "Wrong Password Please Try Again";
	}

	@Override
	public String uploadImage(String userEmailId, MultipartFile file) {
        UserEntity userEntity=userRepository.findOne(userEmailId);
        if(userEntity==null) return "EmailId doesn't exists Please Register";
        
        else {
        	String imageURL=null;
        	try {
        		String[] imageTypeFull=file.getContentType().split("/");
        		String imageType=imageTypeFull[imageTypeFull.length-1];
				imageURL="/home/techbion/git/user-api/images/"+userEmailId+"."+imageType;
				File file2=new File(imageURL);
				if(file2.exists()) file2.delete();
				file.transferTo(file2);
			} catch (IllegalStateException | IOException e) {
				return "Failed to upload the Image, Exception:"+e.getMessage();
			}
        	userEntity.setUserImagePath(imageURL);
        	userEntity=userRepository.save(userEntity);
        	if(userEntity==null) return "Image URL not Saved Please Try again";
        	else return "Image Uploaded";
        }
		
	}

	@Override
	public String getImage(String userEmailId) {
		UserEntity userEntity=userRepository.findOne(userEmailId);
		if(userEntity==null) return "EmailId doesn't exists Please Register";
		else return userEntity.getUserImagePath();
		
	}
    
}
