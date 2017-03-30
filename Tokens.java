package com.iam;

import java.text.ParseException;

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
 public Response getSSOToken(){
      client = clientBuilder.withConfig(config).build();
      WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json/ManagedPeople/authenticate");
     Response response = webTarget.request().header("X-OpenAM-Username",USERNAME).header("X-OpenAM-Password", PASSWORD)
             .header("Content-Type", "application/json").post(Entity.json(""));
     System.out.println(response);
     String str = response.getEntity().toString();
     System.out.println(str);
     JSONObject jsonObj = null;
     String tokenId = null;
         try {
             jsonObj = new JSONObject(str);
             tokenId = jsonObj.get("tokenId").toString();
         } catch (JSONException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
     return Response.ok().entity(tokenId).build();
             //response.readEntity(SSOTokenResponse.class);
 
 }
 
}