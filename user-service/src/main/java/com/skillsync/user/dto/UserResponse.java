package com.skillsync.user.dto;

import com.skillsync.user.model.Role;
import com.skillsync.user.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private Role role;

    // Add this method
    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}