package com.example.linklistapi.controller;

import com.example.linklistapi.models.Link;
import com.example.linklistapi.services.LinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/links")
    public List<Link> getAllLinks() {
        return linkService.getAllLinks();
    }

    @PostMapping("/create-link")
    public Link createLink(@RequestBody Link link) {
        return linkService.createLink(link);
    }
}
