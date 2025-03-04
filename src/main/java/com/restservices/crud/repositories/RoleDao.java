package com.restservices.crud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restservices.crud.beans.Role;

@Repository
public interface RoleDao extends CrudRepository<Role,String>{
    
}
