package com.restservices.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.crud.beans.Credentials;
import com.restservices.crud.services.CredentialService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CredentialController {
    
    @Autowired
    private CredentialService credentialService;

    @PostMapping({"/registerNewUser"})
    public Credentials registerNewUser(@RequestBody Credentials credentials)
    {
        return credentialService.registerNewUser(credentials);
    }

    @GetMapping({"/forAdmin"})
    public String forAdmin() {
        return "This URL is only accessible to Admin";
    }
    
    @GetMapping({"/forUser"})
    public String forUser() {
        return "This URL is only accessible to User";
    }
}
