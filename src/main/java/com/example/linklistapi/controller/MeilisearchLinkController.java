package com.example.linklistapi.controller;

import com.example.linklistapi.api.MeilisearchApi;
import com.example.linklistapi.model.MeilisearchLinkSearchResponse;
import com.example.linklistapi.model.MeilisearchSearchRequest;
import com.example.linklistapi.services.MeilisearchLinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class MeilisearchLinkController implements MeilisearchApi {
    private final MeilisearchLinkService meilisearchLinkService;

    public MeilisearchLinkController(MeilisearchLinkService meilisearchLinkService) {
        this.meilisearchLinkService = meilisearchLinkService;
    }


    @Override
    public ResponseEntity<MeilisearchLinkSearchResponse> searchForLinks(MeilisearchSearchRequest meilisearchSearchRequest) {
        return ResponseEntity.ok(meilisearchLinkService.searchForLinks(meilisearchSearchRequest));
    }
}
