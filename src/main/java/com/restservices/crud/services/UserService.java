package com.restservices.crud.services;

//import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.restservices.crud.beans.User;
import com.restservices.crud.controllers.AddResponse;
import com.restservices.crud.repositories.UserRepository;

@Component
@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    /*static HashMap<Integer,User> userIdMap;

    public UserService()
    {
        userIdMap=new HashMap<Integer,User>();

        User rajKaran=new User(20333148,"RajKaran","CS");
        User niharika=new User(20333198,"Niharika","IT");

        userIdMap.put(1, rajKaran);
        userIdMap.put(2, niharika);
        userIdMap.put(3,new User(20333194,"Swahan","IT"));
    }*/

    public List<User> getAllUsers()
    {
        /*List users=new ArrayList<>(userIdMap.values());
        return users;*/
        return userRepository.findAll();
    }

    public User getUser(int key)
    {
        /*User user=userIdMap.get(key);
        return user;*/
        return userRepository.findById(key).get();
    }
    
    public User getUserbyName(String name)
    {
        /*for(int i:userIdMap.keySet())
        {
            if(userIdMap.get(i).getName().equals(name))
                return userIdMap.get(i);
        }
        return null;*/
        List<User>users=userRepository.findAll();
        for(User u:users)
        {
            if(u.getName().equalsIgnoreCase(name))
                return u;
        }
        return null;
    }

    public User addUser(User user)
    {
        /*user.setId(getMaxId());
        userIdMap.put(getMaxKey(),user);
        return user;*/
        user.setId(getMaxId());
        userRepository.save(user);
        return user;
    }

    /*public int getMaxKey()
    {
        int max=0;
        for(int i:userIdMap.keySet())
        {
            if(i>=max)
                max=i;
        }
        return max+1;
    }*/

    public int getMaxId()
    {
        int max=0;
        /*for(int i:userIdMap.keySet())
        {
            if(userIdMap.get(i).getId()>=max)
                max=userIdMap.get(i).getId();
        }
        return max+1;*/
        List<User>users=userRepository.findAll();
        for(User u:users)
        {
            if(u.getId()>=max)
                max=u.getId();
        }
        return max+1;
    }

    /*public int getKey(int id)
    {
        for(int i:userIdMap.keySet())
        {
            if(userIdMap.get(i).getId()==id)
                return i;
        }
        return -1;
    }*/

    public User updateUser(User user)
    {   
        /*int key=getKey(user.getId());
        if(key>0)
            userIdMap.put(key,user);
        return user;*/
        userRepository.save(user);
        return user;
    }

    public AddResponse deleteUser(int id)
    {
        /*userIdMap.remove(id);*/
        userRepository.deleteById(id);
        AddResponse res=new AddResponse();
        res.setMsg("User Deleted");
        res.setId(id);
        return res;
    }
}
