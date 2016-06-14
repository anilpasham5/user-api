package com.api.tss.users.pojo;

public class UserPojo {

	private String userFirstName;
	private String userLastName;
	private String userEmailId;
	private String userPassword;
	private long userContactNumber;
	private String userStatus;
	private String userToken;
	private String userImagePath;
	public UserPojo() {}
	public UserPojo(String userFirstName, String userLastName, String userEmailId, String userPassword,
			long userContactNumber, String userStatus, String userToken, String userImagePath) {
		super();
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmailId = userEmailId;
		this.userPassword = userPassword;
		this.userContactNumber = userContactNumber;
		this.userStatus = userStatus;
		this.userToken = userToken;
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
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public long getUserContactNumber() {
		return userContactNumber;
	}
	public void setUserContactNumber(long userContactNumber) {
		this.userContactNumber = userContactNumber;
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
	public String getUserImagePath() {
		return userImagePath;
	}
	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}
	@Override
	public String toString() {
		return "UserPojo [userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userEmailId="
				+ userEmailId + ", userPassword=" + userPassword + ", userContactNumber=" + userContactNumber
				+ ", userStatus=" + userStatus + ", userToken=" + userToken + ", userImagePath=" + userImagePath + "]";
	}
		
	
}
