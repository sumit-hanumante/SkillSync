package com.skillsync.user.controller;

import com.skillsync.user.dto.LoginRequest;
import com.skillsync.user.dto.RegisterRequest;
import com.skillsync.user.dto.UserResponse;
import com.skillsync.user.security.JwtUtil;
import com.skillsync.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*; // For @PostMapping, @GetMapping
import org.springframework.http.ResponseEntity;   // For ResponseEntity
import jakarta.validation.Valid;                  // For @Valid

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse response // Add this parameter
    ) {
        UserResponse userResponse = userService.authenticate(request);

        // Generate JWT token and add to headers
        String token = jwtUtil.generateToken(userService.getUserByEmail(request.getEmail()));
        response.setHeader("Authorization", "Bearer " + token);

        return ResponseEntity.ok(userResponse);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}