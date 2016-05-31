
package com.utn.tacs.tacsthree.auth;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.Priorities;

import com.google.inject.Inject;
import com.utn.tacs.tacsthree.auth.Authenticator;
import com.utn.tacs.tacsthree.exceptions.NotAuthorizedException;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	public Authenticator authenticator = null;

	@Inject
	public AuthenticationFilter(Authenticator _authenticator) {
		this.setAuthenticator(_authenticator);
	}

	public Authenticator getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(Authenticator _authenticator) {
		this.authenticator = _authenticator;
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws NotAuthorizedException {
		try {
			// Get the HTTP Authorization header from the request
			String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
			// Check if the HTTP Authorization header is present and formatted
			// correctly
			if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
				throw new NotAuthorizedException("Authorization header must be provided");
			// Extract the token from the HTTP Authorization header
			String token = authorizationHeader.substring("Bearer".length()).trim();
			// Validate the token
			validateToken(token);
		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

	private void validateToken(String token) throws NotAuthorizedException {
		authenticator.authorize(token);
	}
}
