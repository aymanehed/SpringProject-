package com.example.Springboot_project.Demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Demo")
public class DemoController  {
 @GetMapping
    public ResponseEntity<String>sayHello(){
        return ResponseEntity.ok("hello from secured Hello world");
    }
}
