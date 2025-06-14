package com.gentleninja.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        // Use a secure key - no need for Base64 encoding/decoding
        String rawKey = "my_very_secure_secret_key_that_is_long_enough_for_HS256_algorithm";
        // Create SecretKey directly from the raw key bytes
        this.secretKey = Keys.hmacShaKeyFor(rawKey.getBytes());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return createToken(new HashMap<>(), userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        long now = System.currentTimeMillis();
        long expiration = 1000 * 60 * 60 * 10; // 10 hours

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expiration))
                .signWith(secretKey) // Use secretKey directly - no need to specify algorithm
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey) // Use secretKey consistently
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}