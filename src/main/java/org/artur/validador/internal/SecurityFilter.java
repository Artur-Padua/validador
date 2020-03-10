package org.artur.validador.internal;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

	// Provides BASIC-AUTH to all requests
@Provider
public class SecurityFilter implements ContainerRequestFilter {
	
	public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	public static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

	ClientDatabase clientDatabase = new ClientDatabase();
	
	String username;
	String password;
	
	public String getUsername() {
		return username;
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		//System.out.println(authHeader);
		if(authHeader != null && authHeader.size() > 0) {
				// Extracting username and password from Base64 encoded header
			String authToken = authHeader.get(0);
			//System.out.println(authToken);
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			//System.out.println(authToken);
			if (!authToken.equals("Og==")) { // null exception
				byte[] decodedBytes = Base64.getDecoder().decode(authToken);
				String decodedString = new String(decodedBytes);
				//System.out.println(decodedString);
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				if(tokenizer.countTokens() == 2) { // No username:password pair
					username = tokenizer.nextToken();
					//System.out.println(username);
					password = tokenizer.nextToken();
					//System.out.println(password);
						// Checking every username for correct password
				}
				else requestContext.abortWith(Response.status(Status.BAD_REQUEST).build());
					// Lookup for username:password pair in Database and allows access if found
				for(String key : clientDatabase.clientsMap.keySet()) {
					if(key.equals(username) && clientDatabase.clientsMap.get(key).getPassword().equals(password)) {
						return;
					}	
				}
			}
				// denies access on null exception
			else requestContext.abortWith(Response.status(Status.BAD_REQUEST).build());

		}
			// No username:password pair has been found valid on Database
		requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());			
	}

}
