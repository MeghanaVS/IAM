package com.iam;

import javax.xml.bind.annotation.XmlTransient;

public class ForgotPasswordAttributes {
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlTransient
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlTransient
	private String subject;
	
	@XmlTransient
	private String message;

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	@XmlTransient
	private String confirmationId;
	
	@XmlTransient
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getConfirmationId() {
		return confirmationId;
	}

	public void setConfirmationId(String confirmationId) {
		this.confirmationId = confirmationId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	@XmlTransient
	private String tokenId;
	
	@XmlTransient
	private String userpassword;
}
