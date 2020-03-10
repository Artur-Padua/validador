package org.artur.validador;

import java.util.Base64;
import java.util.StringTokenizer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import org.artur.validador.internal.ClientDatabase;
import org.artur.validador.internal.ClientEntity;
import org.artur.validador.internal.Mod11Service;
import org.artur.validador.internal.PostRequestFormat;
import org.artur.validador.internal.SecurityFilter;

	// Implements REST URI for web service
@Path("validCPForCNPJ")
public class Mod11Resource {
	
	Mod11Service mod11Service = new Mod11Service();
	ClientDatabase clientDatabase = new ClientDatabase(); // Dont know how to make code run once yet...
														  // This leads to database malfunction
	SecurityFilter securityFilter = new SecurityFilter();

		// Receiver a CPF/CNPJ through message in JSON format.
		// Return a JSON response informing if CPF/CNPJ is valid or invalid.
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response isCPForCNPJValid(PostRequestFormat message) {
    		// Extracts CPF/CNPJ from JSON message
		Boolean valid = mod11Service.isMod11Valid(message.getNumberToValidate(), 2);
    		// Builds JSON response
    	PostRequestFormat messageResponse = new PostRequestFormat(message.getNumberToValidate(), valid);
    		// Increments nonUniqueRequest from user
    	clientDatabase.addNonUniqueRequest("user"/*securityFilter.getUsername()*/); // Just test
    	clientDatabase.updateValorDevido("user"/*securityFilter.getUsername()*/); // Just test
    		// Sends response
    	return Response.status(Status.OK).entity(messageResponse).build();
    }
    
    	// Sends user data to user
    @Path("/userData")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserData(@HeaderParam(SecurityFilter.AUTHORIZATION_HEADER_KEY) String authToken) {
		//System.out.println("authToken:" + authToken);
    		// Extracting username from Base64 encoded header. SecurityFilter also secures the exceptions
		authToken = authToken.replaceFirst(SecurityFilter.AUTHORIZATION_HEADER_PREFIX, "");
		byte[] decodedBytes = Base64.getDecoder().decode(authToken);
		String decodedString = new String(decodedBytes);
		StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
		String username = tokenizer.nextToken();
			// Fetches Response based on username
		ClientEntity client = clientDatabase.clientsMap.get(username);
	    //System.out.println(" username:" + client.getUsername());
		//		+ " numberOfUniqueRequests:" + client.getNumberOfUniqueRequests() 
		//		+ " numberOfRequests:" + client.getNumberOfRequests() 
		//		+ " lastRequest:" + client.getLastRequest()
		//		+ " dateOfLastRequest:" + client.getDateOflastRequest());
			// Sends Response
		return Response.status(Status.OK)
				.entity(client)
				.build();
	}
	
		// Early alternate test. Return true for valid CPF/CNPJ. Return false for invalid ones
	@Path("/test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String validTest() {
    	//String valid = String.valueOf(mod11Service.isMod11Valid("009.968.211-71", 2)); // CPF valido
    	//String valid = String.valueOf(mod11Service.isMod11Valid("009.968.211-31", 2)); // CPF invalido DV1
    	//String valid = String.valueOf(mod11Service.isMod11Valid("009.968.211-78", 2)); // CPF invalido DV2
    	String valid = String.valueOf(mod11Service.isMod11Valid("000.000.000-00", 2)); // CPF invalido exception case
    	//String valid = String.valueOf(mod11Service.isMod11Valid("24.835.989/0001-11", 2)); // CNPJ valido
    	//String valid = String.valueOf(mod11Service.isMod11Valid("24.835.989/0001-41", 2)); // CNPJ invalido DV1
    	//String valid = String.valueOf(mod11Service.isMod11Valid("24.835.989/0001-12", 2)); // CNPJ invalido DV2
    	//String valid = String.valueOf(mod11Service.isMod11Valid("20.502.283/0001-87", 2)); // CNPJ valido
    	//String valid = String.valueOf(mod11Service.isMod11Valid("00.000.000/0000-00", 2)); // CNPJ exception case
    	//String valid = String.valueOf(mod11Service.isMod11Valid("18781203/0001-28", 2)); // CNPJ valido
    	//String valid = String.valueOf(mod11Service.isMod11Valid("1-8:bgs hhs781 \\//.2!@##%*(()0  3/0bj0a0 1-2;.,8", 2)); // CNPJ valido messed format
    	
        return valid;
    }	
    
}
