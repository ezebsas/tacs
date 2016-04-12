package com.utn.tacs.tacsthree.api.v1;

import java.io.IOException;
import java.util.NoSuchElementException;
import javax.ws.rs.core.Context;
import javax.ws.rs.GET;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.servlet.http.*;
import java.util.HashMap; 


import com.utn.tacs.tacsthree.api.v1.controllers.UsersController;


@Path("api/v1/")
public class RouteProvider {

	private UsersController userController = new UsersController();
        private HashMap<String,HttpSession> sessions = new HashMap<String,HttpSession>();

	@GET
	@Path("/users")
	@Produces("application/json")
	public Response users() {
		try {
			return Response.ok(userController.getAllUsers()).build();
		} catch (IOException e) {
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/users/{id}")
	@Produces("application/json")
	public Response user(@PathParam("id") String rawId) {
		try {
			return Response.ok(userController.getUser(Integer.valueOf(rawId))).build();
		} catch (NumberFormatException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (IOException e) {
			return Response.serverError().build();
		} catch (NoSuchElementException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
        
        
        @POST
        @Path("/users/sign_in")
        public Response authenticate(@FormParam("username") String username,
                @FormParam("password") String password, 
                @Context HttpServletRequest request ) {
            try {
                if(userController.authenticate(username, password)!= null){
                    HttpSession session = request.getSession(); 
                    session.setAttribute("user", username);
                    sessions.put(username,session);
                    
                    return Response.ok(userController
                        .authenticate(username, password)).build();//ToDo: redirect to main
                } else {
                    return Response.status(Status.BAD_REQUEST).build();
                }                
            } catch (IOException e){
                return Response.serverError().build();
            }
                
            }
            
        }

