package com.example.linklistapi.services;

import com.example.linklistapi.models.Link;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MeilisearchLinkService {
    void createNewDocument(Link link) throws JsonProcessingException;
}
