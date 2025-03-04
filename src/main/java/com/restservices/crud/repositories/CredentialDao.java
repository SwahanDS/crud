package com.restservices.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restservices.crud.beans.Credentials;

public interface CredentialDao extends JpaRepository<Credentials,String>{
    
}
