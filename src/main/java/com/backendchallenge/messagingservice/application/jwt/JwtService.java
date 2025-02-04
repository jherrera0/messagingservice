package com.backendchallenge.messagingservice.application.jwt;

import com.backendchallenge.messagingservice.domain.until.ConstJwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;

public class JwtService {
    @Value("${app-security-key}")
    private String secretKey = "mysecretkeymysecretkeymysecretkeymy";

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    public String extractRole(String jwt) {
        return extractAllClaims(jwt).get(ConstJwt.ROLE).toString();
    }
    public Long extractId(String jwt) {
        return Long.parseLong(extractAllClaims(jwt).get(ConstJwt.ID).toString());
    }
    public Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody();
    }

    Key generateKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}