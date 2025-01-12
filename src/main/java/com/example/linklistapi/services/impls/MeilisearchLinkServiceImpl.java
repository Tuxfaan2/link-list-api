package com.example.linklistapi.services.impls;

import com.example.linklistapi.configuration.LinkListApiProperties;
import com.example.linklistapi.models.Link;
import com.example.linklistapi.services.MeilisearchLinkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import org.springframework.stereotype.Service;

@Service
public class MeilisearchLinkServiceImpl implements MeilisearchLinkService {
    private final Index index;
    private final ObjectMapper objectMapper;

    public MeilisearchLinkServiceImpl(ObjectMapper objectMapper, LinkListApiProperties linkListApiProperties) throws JsonProcessingException {
        this.objectMapper = objectMapper;
        Client client = new Client(new Config(linkListApiProperties.getMeilisearchUrl(), linkListApiProperties.getMeilisearchApiKey()));
        client.createIndex("links");
        index = client.getIndex("links");
    }

    @Override
    public void createNewDocument(Link link) throws JsonProcessingException {
        index.addDocuments(objectMapper.writeValueAsString(link));
    }

    private Index getOrCreateIndex(Client client) {
        Index existingIndex = client.getIndex("links");

        client.createIndex("links");
        existingIndex = client.getIndex("links");

        return existingIndex;
    }
}
