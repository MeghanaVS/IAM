package com.iam;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserModel {

	static ClientBuilder clientBuilder = ClientBuilder.newBuilder();
	 static ClientConfig config = new ClientConfig();
	 public static Client client = clientBuilder.withConfig(config).build();
	 static UserAttributes user = new UserAttributes();
	 static ObjectMapper mapper = new ObjectMapper();
	 
	public static String getUidFromSSOToken(String ssoToken) throws JSONException{
	
		WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/sessions").path(ssoToken)
                                  .queryParam("_action", "validate");
		Response response = webTarget.request().header("Content-Type","application/json").post(Entity.json(""));
		 
		String str = response.readEntity(String.class);
	    JSONObject jsonObj = new JSONObject(str);
	    String uid = jsonObj.get("uid").toString();
		/*int statusCode = 200;
		if(statusCode == Response.Status.OK.getStatusCode()){*/
		return uid;
	}
	
	public static UserEmailAttributes GenerateUserEmailConfirmation(UserAttributes user){
		
		UserEmailAttributes useremailattributes = new UserEmailAttributes();
		/*useremailattributes.setTokenId(token);
		useremailattributes.setEmail(user.getMail());
		useremailattributes.setConfirmationId(confirmationId);*/
        
        return useremailattributes;
		
	}
	
	/*public static String getSSOtokenForUser(String username, String password ) throws JSONException{
		String USERNAME = username;
		String PASSWORD = password;
		client = clientBuilder.withConfig(config).build();
	    WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedPeople").path("/authenticate");
	    Response response = webTarget.request().header("X-OpenAM-Username",USERNAME).header("X-OpenAM-Password", PASSWORD)
	            .header("Content-Type", "application/json").post(Entity.json(""));
	    String str = response.readEntity(String.class);
	    JSONObject jsonObj = new JSONObject(str);
	    String usertokenId = jsonObj.get("tokenId").toString();
	   // System.out.println("hello");
	    return usertokenId;
	}*/
	
}