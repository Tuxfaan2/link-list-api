package com.example.linklistapi.services;

import com.example.linklistapi.model.LinkItemDto;
import com.example.linklistapi.model.MeilisearchLinkSearchResponse;
import com.example.linklistapi.model.MeilisearchSearchRequest;

public interface MeilisearchLinkService {
    void createNewDocument(LinkItemDto link);

    MeilisearchLinkSearchResponse searchForLinks(
            MeilisearchSearchRequest meilisearchSearchRequest);

    void deleteLink(LinkItemDto link);
}
