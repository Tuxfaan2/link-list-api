package com.example.linklistapi.configuration;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public Index index(LinkListApiProperties linkListApiProperties) {
        Client client = new Client(new Config(linkListApiProperties.getMeilisearchUrl(),
                                              linkListApiProperties.getMeilisearchApiKey()));
        return client.getIndex("links");
    }
}
