package com.utn.tacs.tacsthree.auth;

import java.io.Serializable;

public class Credentials implements Serializable {

    private String username;
    private String password;
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String _username){
        this.username=_username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String _password){
        this.password=_password;
    }

}