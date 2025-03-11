package com.example.linklistapi.services.impls;

import com.example.linklistapi.configuration.LinkListApiProperties;
import com.example.linklistapi.model.LinkItemDto;
import com.example.linklistapi.model.MeilisearchLinkSearchResponse;
import com.example.linklistapi.model.MeilisearchSearchRequest;
import com.example.linklistapi.services.MeilisearchLinkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.SearchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    public void createNewDocument(LinkItemDto link) {
        try {
            index.addDocuments(objectMapper.writeValueAsString(link));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MeilisearchLinkSearchResponse searchForLinks(MeilisearchSearchRequest meilisearchSearchRequest) {
        return toMeilisearchLinkSearchResponse(index.search(meilisearchSearchRequest.getQ()));
    }

    private MeilisearchLinkSearchResponse toMeilisearchLinkSearchResponse(SearchResult searchResult) {
        MeilisearchLinkSearchResponse meilisearchLinkSearchResponse = new MeilisearchLinkSearchResponse();
        ArrayList<HashMap<String, Object>> hashMap = objectMapper.convertValue(searchResult.getHits(), ArrayList.class);
        List<LinkItemDto> collect = hashMap.stream().map(bla -> objectMapper.convertValue(bla, LinkItemDto.class)).toList();
        meilisearchLinkSearchResponse.setLimit(Optional.of(searchResult.getLimit()));
        meilisearchLinkSearchResponse.setHits(collect);
        return meilisearchLinkSearchResponse;
    }

    private Index getOrCreateIndex(Client client) {
        Index existingIndex = client.getIndex("links");

        client.createIndex("links");
        existingIndex = client.getIndex("links");

        return existingIndex;
    }
}
