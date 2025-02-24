package com.example.linklistapi.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Payload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class JwtTokenUtil {
    private final LinkListApiProperties linkListApiProperties;
    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;
    private final ObjectMapper objectMapper;

    @Autowired
    public JwtTokenUtil(LinkListApiProperties linkListApiProperties, Algorithm algorithm, JWTVerifier jwtVerifier, ObjectMapper objectMapper) {
        this.linkListApiProperties = linkListApiProperties;
        this.algorithm = algorithm;
        this.jwtVerifier = jwtVerifier;
        this.objectMapper = objectMapper;
    }

    public String createToken(User user) throws JsonProcessingException {

        String userJson = objectMapper.writeValueAsString(user);
        Map<String, Object> map = objectMapper.readValue(userJson, new TypeReference<>() {
        });
        map.remove("password");

        Integer validity = linkListApiProperties.getAccessTokenValidity();

        return JWT.create().withSubject(user.getUsername()).withClaim("user", map).withIssuedAt(new Date())
                .withExpiresAt(Instant.now().plus(validity, ChronoUnit.MINUTES))
                .withIssuer(linkListApiProperties.getIssuer()).withJWTId(UUID.randomUUID().toString()).sign(algorithm);
    }

    private Optional<DecodedJWT> decodeJWT(String jwtToken) {

        return Optional.of(jwtVerifier.verify(jwtToken));
    }

    private Map<String, Claim> parseJwtClaims(String token) {

        Optional<DecodedJWT> decodedJWT = decodeJWT(token);
        return decodedJWT.map(Payload::getClaims).orElse(Collections.emptyMap());
    }

    public Map<String, Claim> resolveClaims(HttpServletRequest req) {

        String token = resolveToken(req);
        if (token != null) {
            return parseJwtClaims(token);
        }
        return Collections.emptyMap();

    }

    public boolean validateToken(HttpServletRequest req) {

        String token = resolveToken(req);
        if (token != null) {
            Optional<DecodedJWT> decodedJWT = decodeJWT(token);
            return decodedJWT.isPresent();
        }

        return false;
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(linkListApiProperties.getAccessTokenHeader());
        if (bearerToken != null && bearerToken.startsWith(linkListApiProperties.getAccessTokenPrefix())) {
            return bearerToken.substring(linkListApiProperties.getAccessTokenPrefix().length()).trim();
        }
        return null;
    }
}