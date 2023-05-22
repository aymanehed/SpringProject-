package com.example.Springboot_project.config;

import com.example.Springboot_project.Services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
private final JwtService jwtService;
private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader=request.getHeader("Authorization");//header that contains jwt
        final String jwt;
        final String UserName;// to extract the useremail
        if(authHeader==null||!authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt=authHeader.substring(7);//to skip the bearer word
    UserName=jwtService.ExtractUsername(jwt);//to extract useremail from jwt
   if(UserName!=null && SecurityContextHolder.getContext().getAuthentication()==null) //if user isn't connected)
   {
       UserDetails userDetails=this.userDetailsService.loadUserByUsername(UserName);//get username from database
   if(jwtService.isTokenValid(jwt,userDetails)){
       UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
               userDetails,
               null,//we don't have credentials when creating a new user
               userDetails.getAuthorities()
       );//for update security context
       authToken.setDetails(
               new WebAuthenticationDetailsSource().buildDetails(request)
       );
       SecurityContextHolder.getContext().setAuthentication(authToken);//update security holder
   };
   }
   filterChain.doFilter(request,response);
    }
}
