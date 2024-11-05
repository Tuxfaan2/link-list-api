package com.example.linklistapi.services;

import com.example.linklistapi.models.Link;

import java.util.List;

public interface LinkService {
    List<Link> getAllLinks();

    Link getLinkById(Long id);

    Link createLink(Link link);

    Link updateLink(Link link);

    void deleteLink(Link link);
}
