package com.example.linklistapi.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "link-list-api")
public class LinkListApiProperties {
    private String apiUser;
    private String password;
    private String meilisearchUrl;
    private String meilisearchApiKey;
    private String secretKey;
    private String issuer;
    private Integer accessTokenValidity;
    private String accessTokenHeader;
    private String accessTokenPrefix;

    public String getApiUser() {
        return apiUser;
    }

    public void setApiUser(String apiUser) {
        this.apiUser = apiUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMeilisearchUrl() {
        return meilisearchUrl;
    }

    public void setMeilisearchUrl(String meilisearchUrl) {
        this.meilisearchUrl = meilisearchUrl;
    }

    public String getMeilisearchApiKey() {
        return meilisearchApiKey;
    }

    public void setMeilisearchApiKey(String meilisearchApiKey) {
        this.meilisearchApiKey = meilisearchApiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public String getAccessTokenHeader() {
        return accessTokenHeader;
    }

    public void setAccessTokenHeader(String accessTokenHeader) {
        this.accessTokenHeader = accessTokenHeader;
    }

    public String getAccessTokenPrefix() {
        return accessTokenPrefix;
    }

    public void setAccessTokenPrefix(String accessTokenPrefix) {
        this.accessTokenPrefix = accessTokenPrefix;
    }

    @Bean
    public Algorithm algorithm() {

        return Algorithm.HMAC256(secretKey);
    }

    @Bean
    public com.auth0.jwt.JWTVerifier jwtVerifier(Algorithm algorithm) {

        return JWT.require(algorithm).withIssuer(issuer).build();
    }
}
