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
public class LoginInRequest {
    private String Username;
    private Role role;
    private String Password;
}

