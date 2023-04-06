package com.neobank.spacemoney.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.neobank.spacemoney.model.AccountResponse;
import com.neobank.spacemoney.model.Client;

@Path("/api/client")
public class ClientAPI {

	@Inject
	private Logger log;

	@POST
	public Response createClient(Client client) {
		log.info("Client {} " + client);
		
		String CODE_MEXICO = "+52", CODE_COLOMBIA = "+57", CODE_PERU = "+51";
		int LEGAL_AGE = 18;
		
     		AccountResponse response = new AccountResponse();
		
		    response.isLegalAge = client.age >= LEGAL_AGE;
		    response.isAuthorizedCountry = (client.cellPhoneCountryCode.startsWith(CODE_MEXICO) | client.cellPhoneCountryCode.startsWith(CODE_COLOMBIA) | client.cellPhoneCountryCode.startsWith(CODE_PERU));
		    response.isBlackListed = checkBlackListed(client.rfc);   	    
		    response.isCandidate = (response.isLegalAge & response.isAuthorizedCountry) ^ response.isBlackListed;

		return Response.status(Status.CREATED).entity(response).build();
	}

	private boolean checkBlackListed(String rfc) {
		List<String> blackListed = List.of("CAME9087", "LAGE8763", "ARM2345");
		return blackListed.contains(rfc);
	}

}
