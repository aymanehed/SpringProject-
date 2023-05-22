package com.example.Springboot_project.Services;


import com.example.Springboot_project.Controllers.AuthenticationResponse;
import com.example.Springboot_project.Controllers.LoginInRequest;
import com.example.Springboot_project.Controllers.SignUpRequest;
import com.example.Springboot_project.Controllers.authenticationController;
import com.example.Springboot_project.Entities.User;
import com.example.Springboot_project.Repositories.Role;
import com.example.Springboot_project.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;//encode the password of the user
 private final  JwtService jwtService;
private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(SignUpRequest request) {//create user in database and save it
    User user= User.builder()
            .username(request.getUsername())
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
    userRepository.save(user);//create user
    var jwtToken=jwtService.generatorToken(user);//generate the token of the user
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

    public AuthenticationResponse authenticate(LoginInRequest request) {
authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        )
);

User user=userRepository.findByUsername(request.getUsername())

        .orElseThrow(()-> new UsernameNotFoundException("User does not exist"));//throw Exception

        var jwtToken=jwtService.generatorToken(user);//generate the token of the user
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
