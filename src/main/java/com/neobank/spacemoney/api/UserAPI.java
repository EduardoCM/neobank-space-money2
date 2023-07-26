package com.neobank.spacemoney.api;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.neobank.spacemoney.model.Client;
import com.neobank.spacemoney.model.User;

@Path("/api/user")
public class UserAPI {
	
	@Inject
	private Logger log;
	
	@POST
	public Response createUser(User user) {
		LocalDateTime localDate = LocalDateTime.now();
		
		String welcomeGreating = "Hello " + user.name;
		
		Status status;
		
		
		if(user.age < 18) {
			welcomeGreating += " You are not of age to create an account";
			status = Status.NOT_ACCEPTABLE;
		} else {
		
		
		
		int hour = localDate.getHour();
		log.info("Local Date: " + localDate);
		log.info("Hour: " + hour);
		
		if(hour < 11) {
			welcomeGreating +=  " Good Moorning ";
		}else if (hour < 15) {
			welcomeGreating += " Good Afternoon";
		} else {
			welcomeGreating += " Good Evening";
		}
		
		welcomeGreating += " We send the confirmation of your account to the email " + user.email;
		status = Status.CREATED;
		}
			
		return Response.status(status).entity(welcomeGreating).build();
	}

}
