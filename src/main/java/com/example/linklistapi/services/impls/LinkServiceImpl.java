package com.example.linklistapi.services.impls;

import com.example.linklistapi.models.Link;
import com.example.linklistapi.repositories.LinkRepository;
import com.example.linklistapi.services.LinkService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }


    @Override
    public List<Link> getAllLinks() {
        List<Link> links = new ArrayList<>();
        linkRepository.findAll().forEach(links::add);
        return links;
    }

    @Override
    public Link getLinkById(Long id) {
        return linkRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Link found with id: " + id));
    }

    @Override
    public Link createLink(Link request) {
        return linkRepository.save(request);
    }

    @Override
    public Link updateLink(Link link) {
        return linkRepository.save(link);
    }

    @Override
    public void deleteLink(Link link) {
        linkRepository.delete(link);
    }
}
