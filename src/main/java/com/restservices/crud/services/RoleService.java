package com.restservices.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restservices.crud.beans.Role;
import com.restservices.crud.repositories.RoleDao;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;
    
    public Role createNewRole(Role role)
    {
        return roleDao.save(role);
    }
}
