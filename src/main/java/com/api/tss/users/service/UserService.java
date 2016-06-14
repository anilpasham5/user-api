package com.api.tss.users.service;

import org.springframework.web.multipart.MultipartFile;

import com.api.tss.users.pojo.UserPojo;

public interface UserService {
String addUser(UserPojo userPojo);
boolean isUserEmailIdExists(String userEmailId);
String verifyUser(String userEmailId,String userPassword);
String activateUser(UserPojo userPojo);
String checkMatch(String userEmailId,long userContactNumber);
String forgotPassword(UserPojo userPojo);
String verifyForgotPasswordLink(String userEmailId,String userToken);
String resetPassword(String userEmailId,String userPassword);
UserPojo getUserProfile(String userEmailId);
String updateUser(UserPojo userPojo);
String changePassword(String userEmailId,String userCurrentPassword,String userNewPassword);
String uploadImage(String userEmailId,MultipartFile file);
String getImage(String userEmailId);
}
