package com.api.tss.users.dao;

import org.springframework.web.multipart.MultipartFile;

import com.api.tss.users.models.UserEntity;

public interface UserDAO {
String addUser(UserEntity userEntity);
boolean isUserEmailIdexists(String userEmailId);
String verifyUser(String userEmailId,String userPassword);
String activateUser(UserEntity userEntity);
String checkMatch(String userEmailId,long userContactNumber);
String forgotPassword(UserEntity userEntity);
String verifyForgotPasswordLink(String userEmailId,String userToken);
String resetPassword(String userEmailId,String userPassword);
UserEntity getUserProfile(String userEmailId);
String updateUser(UserEntity userEntity);
String changePassword(String userEmailId,String userCurrentPassword,String userNewPassword);
String uploadImage(String userEmailId,MultipartFile file);
String getImage(String userEmailId);
}
