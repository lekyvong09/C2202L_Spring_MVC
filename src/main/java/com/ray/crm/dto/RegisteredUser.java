package com.ray.crm.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ray.crm.validation.FieldMatch;
import com.ray.crm.validation.ValidEmail;


@FieldMatch.List({
	@FieldMatch(first = "password", second = "matchingPassword", message = "The password must match")
})
public class RegisteredUser {
	
	@NotEmpty(message = "username is required")
	@Size(min = 3, message = "username must have at least 3 characters")
	private String userName;
	
	@NotEmpty(message = "email is required")
	@ValidEmail
	private String email;
	
	@NotEmpty(message = "password is required")
	private String password;
	
	
	private String matchingPassword;

	public RegisteredUser() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
	
}
