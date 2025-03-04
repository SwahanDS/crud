package com.restservices.crud.beans;

public class JwtResponse {
    
    private Credentials credentials;
    private String jwtToken;
    public JwtResponse(Credentials credentials, String jwtToken) {
        this.credentials = credentials;
        this.jwtToken = jwtToken;
    }
    public Credentials getCredentials() {
        return credentials;
    }
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
    public String getJwtToken() {
        return jwtToken;
    }
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    
}
