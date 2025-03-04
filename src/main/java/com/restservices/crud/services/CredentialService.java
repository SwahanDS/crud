package com.restservices.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restservices.crud.beans.Credentials;
import com.restservices.crud.repositories.CredentialDao;

@Service
public class CredentialService {
    
    @Autowired
    private CredentialDao credentialDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Credentials registerNewUser(Credentials credentials)
    {
        credentials.setPassword(getEncodedPassword(credentials.getPassword()));
        return credentialDao.save(credentials);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
