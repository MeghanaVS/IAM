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
	
}