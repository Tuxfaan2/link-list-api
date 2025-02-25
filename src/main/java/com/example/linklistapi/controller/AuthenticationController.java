package com.example.linklistapi.controller;

import com.example.linklistapi.configuration.JwtTokenUtil;
import com.example.linklistapi.models.AuthenticationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login() throws JsonProcessingException {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String token = jwtTokenUtil.createToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);

        return ResponseEntity.ok().headers(buildResponseHeader(token)).body(authenticationResponse);
    }

    private HttpHeaders buildResponseHeader(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return responseHeaders;
    }
}
