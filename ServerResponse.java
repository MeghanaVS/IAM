package com.iam;

/** contains the same HTTP Status code returned by the server */
public class ServerResponse {

	private static int status;
	public static int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;
	public ServerResponse(){}
	public ServerResponse(int status,String message){
		this.status=status;
		this.message=message;
	}
}
