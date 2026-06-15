package com.example.linklistapi.services;

import com.example.linklistapi.model.CreateLinkItemRequest;
import com.example.linklistapi.model.LinkItemDto;

import java.util.List;

public interface LinkService {
    List<LinkItemDto> getAllLinks();

    LinkItemDto getLinkById(Long id);

    LinkItemDto createLink(CreateLinkItemRequest link);

    LinkItemDto updateLink(Long linkId);

    LinkItemDto deleteLink(Long link);
}
