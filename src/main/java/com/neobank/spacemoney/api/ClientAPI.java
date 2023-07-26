package com.neobank.spacemoney.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.neobank.spacemoney.model.Client;
import com.neobank.spacemoney.model.CreateAccountResponse;
import com.neobank.spacemoney.util.Constants;

@Path("/api/client")
public class ClientAPI {

	@Inject
	private Logger log;

	@POST
	public Response createClient(Client client) {
		log.info("Client {} " + client);
		
		CreateAccountResponse response = new CreateAccountResponse();
		
		response.isLegalAge = client.age >= Constants.LEGAL_AGE;
		response.isAuthorizedCountry = (client.cellPhoneCountryCode.startsWith(Constants.CODE_MEXICO) | client.cellPhoneCountryCode.startsWith(Constants.CODE_COLOMBIA) | client.cellPhoneCountryCode.startsWith(Constants.CODE_PERU));
		response.isBlackListed = checkBlackListed(client.rfc);
		
		response.isCandidate = (response.isLegalAge & response.isAuthorizedCountry) ^ response.isBlackListed;
     	
		return Response.status(Status.CREATED).entity(response).build();
	}

	private boolean checkBlackListed(String rfc) {
		List<String> blackListed = List.of("CAME2342", "LAGE3452", "ARM2342");	
		return blackListed.contains(rfc);
	}



}
