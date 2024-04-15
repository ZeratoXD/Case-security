package com.generation.autenticacao.security;

import java.security.Key;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/verify")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtValidator {

    private final Key secretKey;

    public JwtValidator() {
        this.secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyJwt(@RequestBody String jwt) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
            return ResponseEntity.ok("{\"valid\": true}");
        } catch (Exception e) {
            return ResponseEntity.ok("{\"valid\": false}");
        }
    }
}