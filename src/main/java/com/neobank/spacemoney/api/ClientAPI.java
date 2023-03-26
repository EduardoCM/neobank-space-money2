package com.neobank.spacemoney.api;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.neobank.spacemoney.model.Client;

@Path("/api/client")
public class ClientAPI {

	@Inject
	private Logger log;

	@POST
	public Response createClient(Client client) {

		client.isBlacklisted = checkBlackListed(client.rfc);

		client.isLegalAge = client.age > 18;
		client.isAuthorizedCountry = client.cellPhoneCountryCode.startsWith("+52");
		client.isCandidate = client.isLegalAge ^ client.isBlacklisted;

		log.info("New client: " + client);

		return Response.status(Status.CREATED).build();

	}

	private boolean checkBlackListed(String rfc) {
		return false;
	}

}
