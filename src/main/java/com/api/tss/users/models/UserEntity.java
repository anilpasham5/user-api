package com.api.tss.users.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class UserEntity{

	private String userFirstName;
	private String userLastName;
	private String userPassword;
	private String userStatus;
	private String userToken;
	@Id
	private String userEmailId;
	private long userContactNumber;
	private String userImagePath;
	public UserEntity() {}
	public UserEntity(String userFirstName, String userLastName, String userPassword, String userStatus,
			String userToken, String userEmailId, long userContactNumber, String userImagePath) {
		super();
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPassword = userPassword;
		this.userStatus = userStatus;
		this.userToken = userToken;
		this.userEmailId = userEmailId;
		this.userContactNumber = userContactNumber;
		this.userImagePath = userImagePath;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public long getUserContactNumber() {
		return userContactNumber;
	}
	public void setUserContactNumber(long userContactNumber) {
		this.userContactNumber = userContactNumber;
	}
	public String getUserImagePath() {
		return userImagePath;
	}
	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}
	@Override
	public String toString() {
		return "UserEntity [userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userPassword="
				+ userPassword + ", userStatus=" + userStatus + ", userToken=" + userToken + ", userEmailId="
				+ userEmailId + ", userContactNumber=" + userContactNumber + ", userImagePath=" + userImagePath + "]";
	}
	
}
