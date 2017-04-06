package com.iam;

import org.forgerock.opendj.ldap.SearchScope;
import org.forgerock.opendj.ldap.requests.Requests;
import org.forgerock.opendj.ldap.requests.SearchRequest;
import org.forgerock.opendj.ldap.responses.SearchResultEntry;
import org.forgerock.opendj.ldif.ConnectionEntryReader;

import javax.ws.rs.core.Response;

import org.forgerock.opendj.ldap.Connection;
import org.forgerock.opendj.ldap.LDAPConnectionFactory;
import org.forgerock.opendj.ldap.LdapException;
import org.forgerock.opendj.ldap.SearchResultReferenceIOException;

public class LDAPconnections {

	public static Connection getLDAPConnection(){
		final String host = "10.47.86.97";
        int port = 1389;
		final LDAPConnectionFactory factory = new LDAPConnectionFactory(host,port );
        Connection connection = null;
        try {
            connection = factory.getConnection();
            String opendjpwd = "tycoon";
            char [] opendj_password = opendjpwd.toCharArray();
            
            connection.bind("cn=Directory Manager",opendj_password);
			} catch (LdapException e) {
			            e.printStackTrace();
			} finally {
			            if (factory != null) {
			                            factory.close();
			            }
			}
			return connection;
		
	}
	
	public static boolean doesEmailExists(String mail) {
		boolean emailAlreadyExists = false;
		String keyValueCheckInOpenDJ = "(mail=" + mail + ")";
		SearchRequest searchRequest = Requests.newSearchRequest("ou=people,dc=tycoon,dc=io", SearchScope.WHOLE_SUBTREE, keyValueCheckInOpenDJ);
		try (Connection con = getLDAPConnection(); final ConnectionEntryReader reader = con.search(searchRequest)) {
			if (reader.hasNext()) {
				emailAlreadyExists = true;
			}
		} catch (LdapException e) {
			e.printStackTrace();
		}
		return emailAlreadyExists;
	}
	public static String getUserIDbyMail(String mail) throws SearchResultReferenceIOException{
		String keyValueCheckInOpenDJ = "(mail=" + mail + ")";
		String userId = "Something went wrong. Please try again later";
		SearchRequest searchrequest = Requests.newSearchRequest("ou=people,dc=tycoon,dc=io", SearchScope.WHOLE_SUBTREE, keyValueCheckInOpenDJ);
		try (Connection connection = getLDAPConnection();
				final ConnectionEntryReader reader = connection.search(searchrequest)) {

			if (reader.hasNext()) {
				final SearchResultEntry searchResultEntry = reader.readEntry();
				userId = searchResultEntry.getAttribute("uid").firstValueAsString();
				return userId;
			} 
		} catch (LdapException e) {
			e.printStackTrace();
		}
		return userId;
	}
	
}
	


