package com.w1sh.watcher.clients;

import com.w1sh.watcher.clients.common.RequestBuilder;
import com.w1sh.watcher.dtos.GenreDTO;
import com.w1sh.watcher.responses.GenreResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.w1sh.watcher.constants.TmdbConstants.*;

@Slf4j
@AllArgsConstructor
@Component
public class GenreClient {

    private final RestClient restClient;

    public List<GenreDTO> getAllMovieGenres(){
        log.info("Preparing to query TMDb API for all movie genres");
        String json = restClient.get(RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_GENRE, METHOD_MOVIE, METHOD_LIST)
                .withRequestParams(restClient.defaultRequestParams())
                .getRequestUrl());
        return restClient.parseSingle(json, GenreResponse.class)
                .map(GenreResponse::getGenres)
                .orElseThrow();
    }

    public List<GenreDTO> getAllTVGenres(){
        log.info("Preparing to query TMDb API for all tv genres");
        String json = restClient.get(RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_GENRE, METHOD_TV, METHOD_LIST)
                .withRequestParams(restClient.defaultRequestParams())
                .getRequestUrl());
        return restClient.parseSingle(json, GenreResponse.class)
                .map(GenreResponse::getGenres)
                .orElseThrow();
    }
}
