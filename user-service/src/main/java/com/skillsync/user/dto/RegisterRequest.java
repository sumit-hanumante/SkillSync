package com.skillsync.user.dto;

import com.skillsync.user.model.Role;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email")
    private String email;
    private String password;
    private Role role; // Use Role enum, not String
}