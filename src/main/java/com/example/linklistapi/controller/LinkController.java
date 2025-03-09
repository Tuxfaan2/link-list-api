package com.example.linklistapi.controller;

import com.example.linklistapi.api.LinkApi;
import com.example.linklistapi.model.CreateLinkItemRequest;
import com.example.linklistapi.model.LinkItemDto;
import com.example.linklistapi.services.LinkService;
import com.example.linklistapi.services.MeilisearchLinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LinkController implements LinkApi {

    private final LinkService linkService;
    private final MeilisearchLinkService meilisearchLinkService;

    public LinkController(LinkService linkService, MeilisearchLinkService meilisearchLinkService) {
        this.linkService = linkService;
        this.meilisearchLinkService = meilisearchLinkService;
    }

    @Override
    public ResponseEntity<LinkItemDto> createLinkItem(CreateLinkItemRequest createLinkItemRequest) {
        LinkItemDto createdLink = linkService.createLink(createLinkItemRequest);
        meilisearchLinkService.createNewDocument(createdLink);

        return ResponseEntity.ok(createdLink);
    }

    @Override
    public ResponseEntity<List<LinkItemDto>> getAllLinks() {
        List<LinkItemDto> links = linkService.getAllLinks();
        return ResponseEntity.ok(links);
    }
}
