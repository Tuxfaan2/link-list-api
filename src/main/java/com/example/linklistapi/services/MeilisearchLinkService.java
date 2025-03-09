package com.example.linklistapi.services;

import com.example.linklistapi.model.LinkItemDto;

public interface MeilisearchLinkService {
    void createNewDocument(LinkItemDto link);
}
