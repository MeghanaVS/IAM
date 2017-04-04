package com.iam;

public class ChangePassword {

	 private String currentPassword;
	    private String userPassword;
	    
	    public ChangePassword(String currentPassword, String userPassword) {
	        this.currentPassword = currentPassword;
	        this.userPassword = userPassword;
	    }
	    
	    public String getCurrentpassword() {
	        return currentPassword;
	    }

	    public String getUserpassword() {
	        return userPassword;
	    }

}
