package com.example.authservice.dto.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisteredEvent {

    private Long authUserId;
    private String username;

    // profile info
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private String role;
    private LocalDateTime createdAt;
}
