package com.restservices.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.crud.beans.JwtRequest;
import com.restservices.crud.beans.JwtResponse;
import com.restservices.crud.services.JwtService;

@RestController
@CrossOrigin
public class JwtController {
    
    @Autowired
    private JwtService jwtservice;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return jwtservice.createJwtToken(jwtRequest);
    }
}
