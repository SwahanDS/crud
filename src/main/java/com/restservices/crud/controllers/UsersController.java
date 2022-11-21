package com.restservices.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.crud.beans.User;
import com.restservices.crud.services.UserService;

@RestController
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping("/getUsers")
    public List<User> getUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/getUsers/{key}")
    public ResponseEntity<User> getUserbyId(@PathVariable(value = "key") int key)
    {
        //return userService.getUser(key);
        try
        {
            User user= userService.getUser(key);
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getUsers/username")
    public ResponseEntity<User> getUsers(@RequestParam(value="name") String name)
    {
        //return userService.getUserbyName(name);
        try
        {
            User user= userService.getUserbyName(name);
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    @PutMapping("/updateUser/{ps}")
    public ResponseEntity<User> updateUsers(@PathVariable(value = "ps") int ps,@RequestBody User user)
    {
        try
        {
            User ex=userService.getUser(ps);
            ex.setFname(user.getFname());
            ex.setLname(user.getLname());
            ex.setEmail(ex.getFname().toLowerCase()+"."+ex.getLname().toLowerCase()+".lntecc.com");
            ex.setBg(user.getBg());
            User up=userService.updateUser(ex);
            return new ResponseEntity<User>(up,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUser/{key}")
    public AddResponse deleteUsers(@PathVariable(value = "key") int key)
    {
        return userService.deleteUser(key);
    }
}
