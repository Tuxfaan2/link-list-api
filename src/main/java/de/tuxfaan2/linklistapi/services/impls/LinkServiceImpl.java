package de.tuxfaan2.linklistapi.services.impls;

import com.example.linklistapi.model.CreateLinkItemRequest;
import com.example.linklistapi.model.LinkItemDto;
import de.tuxfaan2.linklistapi.models.Link;
import de.tuxfaan2.linklistapi.repositories.LinkRepository;
import de.tuxfaan2.linklistapi.services.LinkService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }


    @Override
    public List<LinkItemDto> getAllLinks() {
        List<Link> links = new ArrayList<>();
        linkRepository.findAll().forEach(links::add);
        return links.stream().map(Link::toDto).collect(Collectors.toList());
    }

    @Override
    public LinkItemDto getLinkById(Long id) {
        return linkRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Link found with id: " + id)).toDto();
    }

    @Override
    public LinkItemDto createLink(CreateLinkItemRequest request) {
        Link link = getLinkFromRequest(request);
        return linkRepository.save(link).toDto();
    }

    @Override
    public LinkItemDto updateLink(Long linkId) {
        Link linkToUpdate = linkRepository.findById(linkId).orElseThrow(() -> new ResourceNotFoundException("No Link found with id: " + linkId));
        Link newLink = linkRepository.save(linkToUpdate);
        return newLink.toDto();
    }

    @Override
    public LinkItemDto deleteLink(Long linkId) {
        Link link = linkRepository.findById(linkId).orElseThrow(() -> new ResourceNotFoundException("No Link found with id: " + linkId));
        linkRepository.delete(link);
        return link.toDto();
    }

    private Link getLinkFromRequest(CreateLinkItemRequest linkItemDto) {
        Link link = new Link();
        link.setDescription(linkItemDto.getDescription());
        link.setTitle(linkItemDto.getTitle());
        link.setUrl(linkItemDto.getUrl());
        return link;
    }

    private Link getLinkFromDto(LinkItemDto linkItemDto) {
        Link link = new Link();
        link.setDescription(linkItemDto.getDescription());
        link.setTitle(linkItemDto.getTitle());
        link.setUrl(linkItemDto.getUrl());
        link.setId(linkItemDto.getId());
        return link;
    }
}
