package com.restservices.crud.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restservices.crud.beans.Credentials;
import com.restservices.crud.beans.JwtRequest;
import com.restservices.crud.beans.JwtResponse;
import com.restservices.crud.repositories.CredentialDao;
import com.restservices.crud.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService{

    @Autowired
    private CredentialDao credentialDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtrequest) throws Exception
    {
        String userName=jwtrequest.getUserName();
        String userPassword=jwtrequest.getUserPassword();
        authenticate(userName, userPassword);

        final UserDetails userDetails=loadUserByUsername(userName);

        String newGeneratedToken=jwtUtil.generateToken(userDetails);

        Credentials cred=credentialDao.findById(userName).get();

        return new JwtResponse(cred, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials cred=credentialDao.findById(username).get();

        if(cred!=null){
            return new User(cred.getUseranme(),cred.getPassword(), 
            getAuthorities(cred));
        }
        else{
            throw new UsernameNotFoundException("Username is not valid");
        }
    }

    private Set getAuthorities(Credentials credentials)
    {
        Set authorites=new HashSet();
        credentials.getRole().forEach(role->{
            authorites.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName())); 
        });
        return authorites;
    }

    private void authenticate(String userName, String userPassword) throws Exception{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        }
        catch(DisabledException e){
            throw new Exception("User is Disabled");
        }
        catch(BadCredentialsException e){
            throw new Exception("Bad Credentials from User");
        }
        
    }
    
}
