package com.example.authservice.controller;

import com.example.authservice.entities.RefreshToken;
import com.example.authservice.entities.UserInfo;
import com.example.authservice.entities.UserRole;
import com.example.authservice.dto.UserInfoDto;
import com.example.authservice.dto.JwtResponseDTO;
import com.example.authservice.service.JwtService;
import com.example.authservice.service.RefreshTokenService;
import com.example.authservice.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class AuthController
{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenServiceImpl;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("auth/v1/signup")
    public ResponseEntity<?> signUp(@RequestBody UserInfoDto userInfoDto) {

        UserInfo user = userDetailsService.signupUser(userInfoDto);

        List<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .toList();

        RefreshToken refreshToken =
                refreshTokenServiceImpl.createRefreshToken(user.getUsername());

        String jwtToken =
                jwtService.generateToken(user.getUsername(), roles);

        return ResponseEntity.ok(
                JwtResponseDTO.builder()
                        .accessToken(jwtToken)
                        .token(refreshToken.getToken())
                        .build()
        );
    }


}
