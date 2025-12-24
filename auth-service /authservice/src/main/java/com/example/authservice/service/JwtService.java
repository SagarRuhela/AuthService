package com.example.authservice.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;

public interface JwtService {

    String extractUsername(String token);

    Instant extractExpiration(String token);

    List<String> extractRoles(String token);

    <T> T extractClaim(String token, Function<io.jsonwebtoken.Claims, T> claimsResolver);

    boolean validateToken(String token, UserDetails userDetails);

    String generateToken(String username, List<String> roles);
}
