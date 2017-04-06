package com.iam;

import javax.xml.bind.annotation.XmlElement;

public class UserEmailAttributes {

	@XmlElement(name = "email")
	private String email;
	
	@XmlElement(name = "confirmationId")
	private String confirmationId;

	@XmlElement(name = "tokenId")
	private String tokenId;
	
	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmationId() {
		return confirmationId;
	}

	public void setConfirmationId(String confirmationId) {
		this.confirmationId = confirmationId;
	}
	
}
