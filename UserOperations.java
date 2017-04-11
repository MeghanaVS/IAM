package com.iam;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iam.Tokens;

import java.util.Random;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.forgerock.opendj.ldap.SearchResultReferenceIOException;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserOperations {
	
	 ClientBuilder clientBuilder = ClientBuilder.newBuilder();
	 ClientConfig config = new ClientConfig();
	 public Client client = clientBuilder.withConfig(config).build();
	 ObjectMapper mapper = new ObjectMapper();
	 static UserAttributes user = new UserAttributes();
	 
	 @GET
	 @Path("/identity")
	 @Produces("application/json")
	 public Response createUser() throws JsonProcessingException, JSONException{
	
		 String tokens = Tokens.getAdminSSOToken();
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
         
         WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedPeople").path("/users").queryParam("_action","create");
         Response response = webTarget.request().header("iplanetDirectoryPro",tokens).header("Content-Type","application/json").post(Entity.json(data));
         String userSSOToken = UserModel.getSSOtokenForUser(user.getUsername(),user.getUserpassword());
         //System.out.println("uid --> " + uid + " SSOtoken --> " + userSSOToken);
	      return Response.status(response.getStatus()).entity(user.getUid()).build();
  }
	 
	 @GET
	 @Path("/getuid/{token}")
	 @Produces("application/json")
	 public Response getUserIDbySSO(@PathParam("token") String tokens) throws JSONException{
	
		 WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/sessions/").path(tokens)
                 .queryParam("_action", "validate");
         Response response = webTarget.request().header("Content-Type","application/json").post(Entity.json(""));
         String str = response.readEntity(String.class);
		 JSONObject jsonObj = new JSONObject(str);
         String uid = jsonObj.get("uid").toString();
         return Response.ok().entity(str).build();
		 
	 }
	 
	 @GET
	 @Path("/delete/{uid}")
	 @Produces("application/json")
	 public Response deleteuser(@PathParam("uid") String uid) throws JSONException{
		 
		 String admintoken = Tokens.getAdminSSOToken();
		 WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedPeople").path("/users/").path(uid);
		 Response response = webTarget.request().header("iplanetDirectoryPro",admintoken).header("Content-Type","application/json").delete();
		 String str = response.readEntity(String.class);
		 JSONObject jsonObj = new JSONObject(str);
		 
		 return Response.ok().entity(str).build();
		 
	 }
	 
	 @GET
	 @Path("/update/{uid}")
	 @Produces("application/json")
	 public Response updateuser(@PathParam("uid") String uid) throws JSONException{
	
		 Random rand=new Random(); 
		 int r = rand.nextInt(100000-1)+1;
		 mapper.setSerializationInclusion(Include.NON_EMPTY);
		 
		 user.setPostalAddress("PESIT"+r);
		 user.setPreferredLanguage("Hindi");
		 String data=null;
		try {
			data = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		 String tokens = Tokens.getAdminSSOToken();
		 WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedPeople").path("/users/").path(uid);
         Response response = webTarget.request().header("iplanetDirectoryPro",tokens).header("Content-Type","application/json").put(Entity.json(data));
         String str = response.readEntity(String.class);
		 JSONObject jsonObj = new JSONObject(str);
		 
         return Response.ok().entity(str).build();
	 }
	 
	 @GET
	 @Path("/updatepwd/{token}")
	 @Produces("application/json")
	 public Response updateuserpassword(@PathParam("token") String token) throws JSONException, JsonProcessingException{
	
		 Random rand=new Random(); 
		 int r = rand.nextInt(100000-1)+1;
		 mapper.setSerializationInclusion(Include.NON_EMPTY);

		 String userid = UserModel.getUidFromSSOToken(token); 
		 String oldPassword = "tyco@123";
		 String newPassword = "PESIT"+r;
		 ChangePassword changePassword = new ChangePassword(oldPassword, newPassword);
		 
		 String data = mapper.writeValueAsString(changePassword);
		 System.out.println(data);
		 WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedPeople").path("/users/").path(userid).queryParam("_action", "changePassword");
		 Response response = webTarget.request().header("iplanetDirectoryPro",Tokens.getAdminSSOToken()).header("Content-Type","application/json").post(Entity.json(data));
         //String str = response.readEntity(String.class);
         //return Response.ok().entity(str).build(); -> returns {}
		 return Response.status(response.getStatus()).entity(Messages.UPDATEPWD).build();
	 }
	 
	 @GET
	 @Path("/getuser/{uid}")
	 @Produces("application/json")
	 public Response getUserDetailsByUid(@PathParam("uid") String uid) throws JSONException{
		 String token = Tokens.getSSOTokenOnly();
		 String userid = UserModel.getUidFromSSOToken(token);
		 String str = null;
		 if(uid.equals(userid)){
			 WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedPeople").path("/users/").path(uid);
			 Response response = webTarget.request().header("iplanetDirectoryPro",token).get();
			 str = response.readEntity(String.class);
		 }
		 return Response.ok().entity(str).build();
	 }

	 @GET
	 @Path("/self")
	 @Produces("application/json")
	 public Response RegisterUser() throws JSONException{
		 
		 Random rand=new Random(); 
		 int r = rand.nextInt(100000-1)+1;
		 String uid = UUID.randomUUID().toString();
		 emailAttributes self = new emailAttributes();
		 
		 self.setUsername(uid);
		 self.setUid(uid);
		 self.setMiddleName("IAM");
		 self.setCn(uid);
		 self.setPreferredLanguage("English");
		 self.setUsername(uid);
		 self.setUserpassword("tyco@123");
		 self.setEmail("iamprojectdemo@gmail.com");
		 self.setIsRegistering("false");
		 self.setInetuserstatus("Inctive");
		 self.setPostalAddress("PESIT");
		 self.setTelephoneNumber(String.valueOf(r));
		 self.setSn("IAM");
		 self.setPasswordPolicy("iam@1234");
		 self.setSecurityQuestion1("what's your favourite colour?");
		 self.setSecurityQuestion2("what's your place name?");
		 self.setSecurityQuestion3("what's your PAN card number?");
		 self.setSecurityAnswer1("black");
		 self.setSecurityAnswer2("bengaluru");
		 self.setSecurityAnswer3("IAM1234IAM");
		 self.setSecret("PESIT");
		 
         String data = null;
		try {
			data = mapper.writeValueAsString(self);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
         System.out.println(data);
         WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedPeople").path("/users").queryParam("_action","register");
         Response response = webTarget.request().header("Content-Type","application/json").post(Entity.json(data));
         
         String str = response.readEntity(String.class);;
		 System.out.println(str);
         return Response.ok().entity(str).build();
  
	 }
         
/*	 @GET
	 @Path("/forgotpwd/{email}")
	 @Produces("application/json")
	 public Response ForgotUserPassword(@PathParam("email") String email){
		 
		 String json_body;
		 String stage = "INITIAL_STAGE";
		 try {
			String userid = LDAPconnections.getUserIDbyMail(email);
		} catch (SearchResultReferenceIOException e) {
			e.printStackTrace();
		}
		 
	 }*/
	 
}