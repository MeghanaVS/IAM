package com.iam;

import javax.ws.rs.GET;
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

@Path("/tokens")
public class Tokens {
 private static Client client;
 static ClientBuilder clientBuilder = ClientBuilder.newBuilder();
 static ClientConfig config = new ClientConfig();
 
 public static final String USERNAME="iamadmin";
 public static final String PASSWORD="iam@1234";
 static String refresh_token = null;
 static String clientTokenUser = "Basic SUFNdXNlcnM6aWFtQDEyMzQ=";
 static String clientTokenDevice = "Basic SUFNZGV2aWNlczppYW1AMTIzNA==";
 @GET
 @Path("/sso")
 @Produces("application/json")
 public static Response getSSOToken() throws JSONException{
      client = clientBuilder.withConfig(config).build();
      WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedPeople").path("/authenticate");
     Response response = webTarget.request().header("X-OpenAM-Username",USERNAME).header("X-OpenAM-Password", PASSWORD)
             .header("Content-Type", "application/json").post(Entity.json(""));
     String str = response.readEntity(String.class);
     JSONObject jsonObj = null;
         jsonObj = new JSONObject(str);
         String tokenId = jsonObj.get("tokenId").toString();
     return Response.status(200).entity(str).build();
             //response.readEntity(SSOTokenResponse.class);  
 }
 public static String getSSOTokenOnly() throws JSONException{
      Response resp=getSSOToken();
      String tokenJsonString = resp.getEntity().toString();
      JSONObject tokenJson = new JSONObject(tokenJsonString);
      String token = tokenJson.get("tokenId").toString();
      return token;
 }
 
 @GET
 @Path("/admin")
 @Produces("application/json")
 public static String getAdminSSOToken() throws JSONException{
     client = clientBuilder.withConfig(config).build();
     WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/authenticate");
    Response response = webTarget.request().header("X-OpenAM-Username","amadmin").header("X-OpenAM-Password", "TycoNET12")
            .header("Content-Type", "application/json").post(Entity.json(""));
    String str = response.readEntity(String.class);
    JSONObject jsonObj = null;
        jsonObj = new JSONObject(str);
        String tokenId = jsonObj.get("tokenId").toString();
        System.out.println(tokenId);
		return tokenId;
 }
 
 @GET
 @Path("/other")
 @Produces("application/json")
 public static String getOtherUserSSOToken() throws JSONException{
     client = clientBuilder.withConfig(config).build();
     WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/authenticate");
    Response response = webTarget.request().header("X-OpenAM-Username","user1").header("X-OpenAM-Password", "tyco@123")
            .header("Content-Type", "application/json").post(Entity.json(""));
    String str = response.readEntity(String.class);
    JSONObject jsonObj = null;
        jsonObj = new JSONObject(str);
        String tokenId = jsonObj.get("tokenId").toString();
        System.out.println(tokenId);
		return tokenId;
 }
 
 @GET
 @Path("/access")
 @Produces("application/json")
 public static Response getAccessToken() throws JSONException{
      client = clientBuilder.withConfig(config).build();

      WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/oauth2/access_token").queryParam("grant_type", "password")
              .queryParam("username", "IAMdevices")
              .queryParam("password", "iam@1234")
              .queryParam("scope", "cn")
              .queryParam("realm", "/ManagedDevices");
      Response response = webTarget.request().header("Authorization",clientTokenDevice).post(Entity.json(""));
      String str = response.readEntity(String.class);
      System.out.println(str);
      JSONObject jsonObj = null;
          jsonObj = new JSONObject(str);
          String access_token = jsonObj.get("access_token").toString();
          refresh_token = jsonObj.get("refresh_token").toString();
          
      return Response.ok().entity(str).build();

 }
 //Get access token from refresh token
	 @GET
	 @Path("/refresh")
	 @Produces("application/json")
	 public static Response getAccessTokenFromRefreshToken() throws JSONException{
		 WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/oauth2/access_token").queryParam("grant_type", "refresh_token")
                 .queryParam("refresh_token", refresh_token)
                 .queryParam("realm", "/ManagedDevices");
		 Response response = webTarget.request().header("Authorization",clientTokenDevice).post(Entity.json(""));
		 //System.out.println(refresh_token);
	      String str = response.readEntity(String.class);
	      
	      return Response.ok().entity(str).build();
		 
	 }
	
 
}