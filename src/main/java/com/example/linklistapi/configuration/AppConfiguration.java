package com.example.linklistapi.configuration;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.exceptions.MeilisearchApiException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public Index index(LinkListApiProperties linkListApiProperties) {
        Client client = new Client(
                new Config(linkListApiProperties.getMeilisearchUrl(),
                        linkListApiProperties.getMeilisearchApiKey()));
        try {
            return client.getIndex("links");
        } catch (MeilisearchApiException e) {
            client.createIndex("links");
        }
        return client.getIndex("links");
    }
}
