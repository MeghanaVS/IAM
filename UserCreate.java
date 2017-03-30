package com.iam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iam.Tokens;
import com.iam.Usermodel;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserOperations {
	
	 private Client client;
	 ClientBuilder clientBuilder = ClientBuilder.newBuilder();
	 ClientConfig config = new ClientConfig();
	 Tokens token = new Tokens();

	 @GET
	 @Path("/identity")
	 @Produces("application/json")
	 public Response createUser() throws JsonProcessingException, JSONException{
		 ObjectMapper mapper = new ObjectMapper();
		 UserAttributes user = new UserAttributes();
		 client = clientBuilder.withConfig(config).build();
		 Random rand=new Random(); 
		 int r = rand.nextInt(100000-1)+1;
		 
		 String uid = UUID.randomUUID().toString();
		 
		 user.setUsername(uid);
		 user.setUid(uid);
		 user.setMiddleName("IAM");
		 user.setCn(uid);
		 user.setPreferredLanguage("English");
		 user.setUsername(uid);
		 user.setUserpassword("tyco@123");
		 user.setMail(r+"@gmail.com");
		 user.setIsRegistering("false");
		 user.setInetuserstatus("active");
		 user.setPostalAddress("PESIT");
		 user.setTelephoneNumber(String.valueOf(r));
		 user.setSn("IAM");
		 user.setPasswordPolicy("iam@1234");
		 user.setSecurityQuestion1("what's your favourite colour?");
		 user.setSecurityQuestion2("what's your place name?");
		 user.setSecurityQuestion3("what's your PAN card number?");
		 user.setSecurityAnswer1("black");
		 user.setSecurityAnswer2("bengaluru");
		 user.setSecurityAnswer3("IAM1234IAM");
		 user.setSecret("PESIT");
		 
         String data = mapper.writeValueAsString(user);
         //System.out.println(data);
	     WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json/ManagedPeople/users?_action=create");
		 Response response = webTarget.request().header("iplanetDirectoryPro",token.getSSOTokenOnly()).header("Content-Type","application/json").post(Entity.json(data));
		 String str = response.readEntity(String.class);
		 System.out.println(str);
		 
	      return Response.ok().entity(user.getUid()).build();
  }

}