package com.example.linklistapi.controller;

import com.example.linklistapi.models.Link;
import com.example.linklistapi.services.LinkService;
import com.example.linklistapi.services.MeilisearchLinkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LinkController {

    private final LinkService linkService;
    private final MeilisearchLinkService meilisearchLinkService;

    public LinkController(LinkService linkService, MeilisearchLinkService meilisearchLinkService) {
        this.linkService = linkService;
        this.meilisearchLinkService = meilisearchLinkService;
    }

    @GetMapping("/links")
    public List<Link> getAllLinks() {
        return linkService.getAllLinks();
    }

    @PostMapping("/create-link")
    public Link createLink(@RequestBody Link link) throws JsonProcessingException {
        Link createdLink = linkService.createLink(link);
        meilisearchLinkService.createNewDocument(createdLink);
        return createdLink;
    }
}
