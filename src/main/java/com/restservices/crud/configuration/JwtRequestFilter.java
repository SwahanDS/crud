package com.restservices.crud.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.restservices.crud.services.JwtService;
import com.restservices.crud.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        final String header= request.getHeader("AUTHORIZATION");
        
        String jwtToken=null;
        String userName=null;
        if(header!= null && header.startsWith("Bearer "))
        {
            jwtToken =header.substring(7);
            try{
                userName = jwtUtil.getUserNameFromToken(jwtToken);
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("Unable to get JWT token");
            }
            catch(ExpiredJwtException e)
            {
                System.out.println("Jwt Token is expired");
            }
        }
        else{
            System.out.println("Jwt token does not start with bearer");
        }

        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails UserDetails=jwtService.loadUserByUsername(userName);

            if(jwtUtil.validateToken(jwtToken,UserDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(UserDetails,
                                null,
                                UserDetails.getAuthorities()
                );

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
    
}
