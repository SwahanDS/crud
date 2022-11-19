package com.restservices.crud.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restservices.crud.beans.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer>{
    
}
