package com.iam;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class UserAttributes {

	@XmlTransient
	private String username;
	
	@XmlElement(name = "userpassword")
	private String userpassword;
	
	@XmlTransient
	private String uid;
	
	@XmlTransient
	private String inetuserstatus;

	@XmlTransient
	private String isRegistering;
	
	@XmlElement(name = "email")
	private String mail;

	@XmlElement(name = "firstName")
	private String cn;
	
	@XmlElement(name = "surname")
	private String sn;
	
	@XmlElement(name = "address")
	private String postalAddress;
	
	@XmlElement(name = "contactInfo")
	private String telephoneNumber;
	
	@XmlElement(name = "middleName")
	private String middleName;
	
	@XmlElement(name = "language")
	private String preferredLanguage;
	
	@XmlTransient
	private String securityQuestion1;

	@XmlTransient
	private String securityQuestion2;

	@XmlTransient
	private String securityQuestion3;

	@XmlTransient
	private String securityAnswer1;

	@XmlTransient
	private String securityAnswer2;

	@XmlTransient
	private String securityAnswer3;

	@XmlTransient
	private String secret;

	@XmlElement
	private List<Question> questions;
	
	@XmlElement
	private String passwordPolicy;
	
	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getInetuserstatus() {
		return inetuserstatus;
	}

	public void setInetuserstatus(String inetuserstatus) {
		this.inetuserstatus = inetuserstatus;
	}

	public String getIsRegistering() { return isRegistering; }

	public void setIsRegistering(String isRegistering) { this.isRegistering = isRegistering; }

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPasswordPolicy() {
		return passwordPolicy;
	}

	public void setPasswordPolicy(String passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
	}

	public String getSecurityQuestion3() {
		return securityQuestion3;
	}

	public void setSecurityQuestion3(String securityQuestion3) {
		this.securityQuestion3 = securityQuestion3;
	}

	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getSecurityAnswer2() {
		return securityAnswer2;
	}

	public void setSecurityAnswer2(String securityAnswer2) {
		this.securityAnswer2 = securityAnswer2;
	}

	public String getSecurityAnswer3() {
		return securityAnswer3;
	}

	public void setSecurityAnswer3(String securityAnswer3) {
		this.securityAnswer3 = securityAnswer3;
	}

	public String getSecurityAnswer1() {
		return securityAnswer1;
	}

	public void setSecurityAnswer1(String securityAnswer1) {
		this.securityAnswer1 = securityAnswer1;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}

