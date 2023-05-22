package com.example.Springboot_project.Controllers;

import com.example.Springboot_project.Repositories.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String Username;
    private String Firstname;
    private String Lastname;
    private String Email;
    private String Password;
    private Role role;
}
