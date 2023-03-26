package com.neobank.spacemoney.api;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.neobank.spacemoney.model.AccountResponse;
import com.neobank.spacemoney.model.Account;
import com.neobank.spacemoney.model.Client;

@Path("/api/client")
public class ClientAPI {

	@Inject
	private Logger log;

	@POST
	public Response createClient(Client client) {

		AccountResponse accountResponse = new AccountResponse();

		accountResponse.isBlacklisted = checkBlackListed(client.rfc);

		accountResponse.isLegalAge = client.age > 18;
		accountResponse.isAuthorizedCountry = client.cellPhoneCountryCode.startsWith("+52");
		accountResponse.isCandidate = accountResponse.isLegalAge ^ accountResponse.isBlacklisted;

		return Response.status(Status.CREATED).entity(accountResponse).build();

	}

	private boolean checkBlackListed(String rfc) {
		return false;
	}

}
