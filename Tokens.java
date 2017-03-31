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
 private Client client;
 ClientBuilder clientBuilder = ClientBuilder.newBuilder();
 ClientConfig config = new ClientConfig();
 
 public static final String USERNAME="iamadmin";
 public static final String PASSWORD="iam@1234";
 
 @GET
 @Path("/sso")
 @Produces("application/json")
 public Response getSSOToken() throws JSONException{
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
 public String getSSOTokenOnly() throws JSONException{
      Response resp=getSSOToken();
      String tokenJsonString = resp.getEntity().toString();
      JSONObject tokenJson = new JSONObject(tokenJsonString);
      String token = tokenJson.get("tokenId").toString();
      return token;
 }
 
 @GET
 @Path("/admin")
 @Produces("application/json")
 public String getAdminSSOToken() throws JSONException{
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
 
}