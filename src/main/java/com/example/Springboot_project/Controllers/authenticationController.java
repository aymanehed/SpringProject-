package com.example.Springboot_project.Controllers;

import com.example.Springboot_project.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class authenticationController {
    private final AuthenticationService authenticationService;
    Logger logger = LoggerFactory.getLogger(authenticationController.class);
    @PostMapping("/SignUp")
    public ResponseEntity<AuthenticationResponse>SignUp(
            @RequestBody SignUpRequest request //email,password of the user
    ){    //this.logger.info(request.getUsername());
return ResponseEntity.ok(authenticationService.register(request));

    }
    @PostMapping("/LogIn")
    public ResponseEntity<AuthenticationResponse>LogIn(
            @RequestBody LoginInRequest request //email,password of <the user
    ){
        //this.logger.info(request.getPassword());
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }
}
