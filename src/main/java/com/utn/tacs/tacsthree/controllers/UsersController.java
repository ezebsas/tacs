package com.utn.tacs.tacsthree.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users")
public class UsersController {

  @GET
  @Path("/{id}")
  public Response getUser(@PathParam("id") Integer id) {
    String result = "<h1>Users</h1> Requested User id: " + id;
    return Response.status(200).entity(result).build();
  }
}
