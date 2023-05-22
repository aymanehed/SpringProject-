package com.example.Springboot_project.config;
import com.example.Springboot_project.Repositories.UserRepository;
import com.mysql.cj.protocol.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username ->userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not Found"));//for return exception;//Get username from database
    }
   @Bean
   public AuthenticationProvider authenticationProvider() {
   DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
       authProvider.setUserDetailsService(userDetailsService());//type of extract data of user
       authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
       return config.getAuthenticationManager();//it's responsible to manage the authentication,helps users to authenticate
    }
}
