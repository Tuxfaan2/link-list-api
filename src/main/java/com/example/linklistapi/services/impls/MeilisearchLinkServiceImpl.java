package com.example.linklistapi.services.impls;

import com.example.linklistapi.model.LinkItemDto;
import com.example.linklistapi.model.MeilisearchLinkSearchResponse;
import com.example.linklistapi.model.MeilisearchSearchRequest;
import com.example.linklistapi.services.MeilisearchLinkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.model.SearchResultPaginated;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeilisearchLinkServiceImpl implements MeilisearchLinkService {
    private final Index index;
    private final ObjectMapper objectMapper;

    public MeilisearchLinkServiceImpl(Index index, ObjectMapper objectMapper) {
        this.index = index;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createNewDocument(LinkItemDto link) {
        try {
            index.addDocuments(objectMapper.writeValueAsString(link), "id");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public MeilisearchLinkSearchResponse searchForLinks(
            MeilisearchSearchRequest meilisearchSearchRequest) {
        SearchRequest searchRequest = new SearchRequest(meilisearchSearchRequest.getQ());
        searchRequest.setPage(meilisearchSearchRequest.getPage());
        searchRequest.setHitsPerPage(meilisearchSearchRequest.getHitsPerPage());
        return toMeilisearchLinkSearchResponse(
                searchRequest);
    }

    @Override
    public void deleteLink(LinkItemDto link) {
        index.deleteDocument(link
                .getId()
                .toString());
    }

    private MeilisearchLinkSearchResponse toMeilisearchLinkSearchResponse(
            SearchRequest searchRequest) {
        SearchResultPaginated searchResultPaginated = (SearchResultPaginated) index.search(searchRequest);
        MeilisearchLinkSearchResponse meilisearchLinkSearchResponse =
                new MeilisearchLinkSearchResponse();

        List<LinkItemDto> links = searchResultPaginated
                .getHits()
                .stream()
                .map(link -> objectMapper.convertValue(link, LinkItemDto.class))
                .toList();

        meilisearchLinkSearchResponse.setLimit(
                searchResultPaginated.getHitsPerPage());
        meilisearchLinkSearchResponse.setHits(links);
        meilisearchLinkSearchResponse.setPage(searchResultPaginated.getPage());
        meilisearchLinkSearchResponse.setTotalPages(searchResultPaginated.getTotalPages());
        return meilisearchLinkSearchResponse;
    }
}
