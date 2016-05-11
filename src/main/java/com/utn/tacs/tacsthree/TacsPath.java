package com.utn.tacs.tacsthree;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

@Path("/")
public class TacsPath {
	
  @GET
  public Response index() {
    return Response.ok("Holis").build();
  }
}