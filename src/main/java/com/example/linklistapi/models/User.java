package com.example.linklistapi.models;

import com.example.linklistapi.configuration.security.AccessAuthority;
import com.example.linklistapi.configuration.security.DemoAuthority;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {
    private Long customerId;
    private String username;
    private String password;
    private List<DemoAuthority> authorities;

    @Override
    public Collection<DemoAuthority> getAuthorities() {

        if (authorities == null) {
            authorities = new ArrayList<>();
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonSetter("authorities")
    public void setAuthorities(String[] auth) {

        this.authorities = new ArrayList<>();
        Arrays.stream(auth).map(AccessAuthority::fromValue).forEach(a -> authorities.add(a));
    }
}