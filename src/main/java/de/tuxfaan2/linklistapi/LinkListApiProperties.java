package de.tuxfaan2.linklistapi;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "link-list-api")
public class LinkListApiProperties {
    private String meilisearchUrl;
    private String meilisearchApiKey;

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
