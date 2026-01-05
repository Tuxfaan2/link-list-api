package de.tuxfaan2.linklistapi.services.impls;

import com.example.linklistapi.model.LinkItemDto;
import com.example.linklistapi.model.MeilisearchLinkSearchResponse;
import com.example.linklistapi.model.MeilisearchSearchRequest;
import de.tuxfaan2.linklistapi.services.MeilisearchLinkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return toMeilisearchLinkSearchResponse(
                index.search(meilisearchSearchRequest.getQ()));
    }

    @Override
    public void deleteLink(LinkItemDto link) {
        index.deleteDocument(link
                .getId()
                .toString());
    }

    private MeilisearchLinkSearchResponse toMeilisearchLinkSearchResponse(
            SearchResult searchResult) {
        MeilisearchLinkSearchResponse meilisearchLinkSearchResponse =
                new MeilisearchLinkSearchResponse();
        List<LinkItemDto> links = searchResult
                .getHits()
                .stream()
                .map(link -> objectMapper.convertValue(link, LinkItemDto.class))
                .toList();

        meilisearchLinkSearchResponse.setLimit(
                Optional.of(searchResult.getLimit()));
        meilisearchLinkSearchResponse.setHits(links);
        return meilisearchLinkSearchResponse;
    }
}
