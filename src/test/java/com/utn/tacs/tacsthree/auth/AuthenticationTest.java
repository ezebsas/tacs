package com.utn.tacs.tacsthree.auth;

import com.utn.tacs.tacsthree.api.v1.RouteProvider;
import com.utn.tacs.tacsthree.persistence.mocks.CharacterGroupTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.MarvelCharacterTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.UserTestRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class AuthenticationTest {
    	
        public RouteProvider route;
        public String header;
        public String payload;
        public String signature;
        public String username= "Tom";
        public String password="tacs1234";
        public Authenticator authenticator;
        public Key key = MacProvider.generateKey();
    
	@Before
	public void setUp() {
		route = new RouteProvider();
		
                route.userRepo = UserTestRepository.getInstance();
		route.characRepo = MarvelCharacterTestRepository.getInstance();
		route.groupsRepo = CharacterGroupTestRepository.getInstance();
		((UserTestRepository) route.userRepo).restart();
		((MarvelCharacterTestRepository) route.characRepo).restart();
		((CharacterGroupTestRepository) route.groupsRepo).restart();   
                
                authenticator= new Authenticator(route);
        
        }
        
        @Test
        public void loginTest(){
            
            String jwt=authenticator.login(username, password);
            
            assertEquals(Jwts.parser().setSigningKey(authenticator.key).parseClaimsJws(jwt).getBody().getSubject(),(username));
        }
        
}
