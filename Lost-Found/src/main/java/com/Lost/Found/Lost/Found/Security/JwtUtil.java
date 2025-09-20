package com.Lost.Found.Lost.Found.Security;

import com.Lost.Found.Lost.Found.Model.Role; // use your enum
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expiration = 15 * 60 * 1000; // 15 minutes

    // Generate token with username and role
    public String generateToken(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role.name())
                .setIssuer("myApp")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    // Validate token
    public boolean validateToken(String token, String username) {
        try {
            String tokenUsername = getUsername(token);
            return username.equals(tokenUsername) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    // Extract username
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Check if token expired
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Extract role
    public Role getRole(String token){
        String roleStr = (String) getClaims(token).get("role");
        return Role.valueOf(roleStr);
    }

    // Extract claims
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
