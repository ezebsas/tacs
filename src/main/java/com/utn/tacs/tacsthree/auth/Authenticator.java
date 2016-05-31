package com.utn.tacs.tacsthree.auth;

import java.util.Calendar;
import java.util.Date;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;


import com.utn.tacs.tacsthree.api.v1.RouteProvider;
import com.utn.tacs.tacsthree.exceptions.NotAuthorizedException;
import com.utn.tacs.tacsthree.models.User;

import com.utn.tacs.tacsthree.persistence.UserDAO;
    
public class Authenticator {
	
	private UserDAO userRepo;
	
    public Key key = MacProvider.generateKey();
    public RouteProvider route;
    
    public Authenticator(UserDAO repo) {
    	this.userRepo = repo;

    }

    public Authenticator(RouteProvider route) {
        setRouteProvider(route);
    }    
    
    public void setRouteProvider(RouteProvider route){
        this.route=route;
    }

    public String login(String username, String password){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        Date expirationDate =cal.getTime();
        String jwt=null;
        
        User user = userRepo.get(username);
        
        if(user.getPassword().equals(password)){
            jwt = Jwts.builder().setSubject(username)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
        } else {
            new NotAuthorizedException("incorrect password"); 
        }
        
        return jwt;
    }
    
    public void authorize(String compactJwt){
        
        Date currentDate = new Date();
        
        String username=Jwts.parser().setSigningKey(key).parseClaimsJws(compactJwt).getBody().getSubject();
        Date expirationDate=Jwts.parser().setSigningKey(key).parseClaimsJws(compactJwt).getBody().getExpiration();
        
        userRepo.get(username);
        if(currentDate.after(expirationDate)){
            throw new NotAuthorizedException("session expired");
        }
    }
}
