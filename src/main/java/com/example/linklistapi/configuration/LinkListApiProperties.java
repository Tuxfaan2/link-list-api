package com.example.linklistapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "link-list-api")
public class LinkListApiProperties {
    private String apiUser;
    private String password;
    private String meilisearchUrl;
    private String meilisearchApiKey;

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
}
