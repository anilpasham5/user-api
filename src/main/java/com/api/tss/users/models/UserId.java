package com.api.tss.users.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class UserId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userEmailId;
	private long userContactNumber;
	public UserId() {}
	public UserId(String userEmailId, long userContactNumber) {
		super();
		this.userEmailId = userEmailId;
		this.userContactNumber = userContactNumber;
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
	@Override
	public String toString() {
		return "UserId [userEmailId=" + userEmailId + ", userContactNumber=" + userContactNumber + "]";
	}
	
}
