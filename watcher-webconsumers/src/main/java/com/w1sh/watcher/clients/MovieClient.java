package com.w1sh.watcher.clients;

import com.w1sh.watcher.clients.common.RequestBuilder;
import com.w1sh.watcher.clients.common.RequestParameter;
import com.w1sh.watcher.dtos.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.w1sh.watcher.constants.TmdbConstants.*;

@Slf4j
@AllArgsConstructor
@Component
public class MovieClient {

    private final RestClient restClient;

    public Optional<MovieDTO> findById(Integer id, RequestParameter parameter) {
        log.info("Preparing to query TMDb API for the movie with id \"{}\"", id);
        String json = restClient.get(RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_MOVIE)
                .withId(id)
                .withRequestParams(restClient.defaultRequestParams())
                .withRequestParams(parameter)
                .getRequestUrl());
        return restClient.parseSingle(json, MovieDTO.class);
    }

    /*public List<MovieDTO> findRecommendationsForId(Integer id) {
        log.info("Preparing to query TMDb API for a list of recommendations for movie with id \"{}\"", id);
        String json = restClient.get(RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_MOVIE)
                .withId(id)
                .withMethods(METHOD_RECOMMENDATIONS)
                .withRequestParams(restClient.defaultRequestParams())
                .getRequestUrl());
        return restClient.parse(json);
    }

    public List<MovieDTO> findSimilarForId(Integer id) {
        log.info("Preparing to query TMDb API for a list of similar movies for movie with id \"{}\"", id);
        String json = restClient.get(RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_MOVIE)
                .withId(id)
                .withMethods(METHOD_SIMILAR)
                .withRequestParams(restClient.defaultRequestParams())
                .getRequestUrl());
        return restClient.parse(json);
    }*/

    public List<MovieDTO> findPopular(){
        log.info("Preparing to query TMDb API for popular movies");
        String json = restClient.get(RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_MOVIE, METHOD_POPULAR)
                .withRequestParams(restClient.defaultPagedRequestParams())
                .getRequestUrl());
        return restClient.parse(json);
    }

    public List<MovieDTO> findNowPlaying(){
        log.info("Preparing to query TMDb API for now playing movies");
        String json = restClient.get(RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_MOVIE, METHOD_NOW_PLAYING)
                .withRequestParams(restClient.defaultPagedRequestParams())
                .getRequestUrl());
        return restClient.parse(json);
    }

    public List<MovieDTO> findTopRated(){
        log.info("Preparing to query TMDb API for top rated movies");
        String json = restClient.get(RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_MOVIE, METHOD_TOP_RATED)
                .withRequestParams(restClient.defaultPagedRequestParams())
                .getRequestUrl());
        return restClient.parse(json);
    }

    public List<MovieDTO> findUpcoming(){
        log.info("Preparing to query TMDb API for upcoming movies");
        String json = restClient.get(RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_MOVIE, METHOD_UPCOMING)
                .withRequestParams(restClient.defaultPagedRequestParams())
                .getRequestUrl());
        return restClient.parse(json);
    }

    public List<MovieDTO> findByTitle(String title){
        log.info("Preparing to query TMDb API for movies with title containing \"{}\"", title);
        RequestParameter searchParam = new RequestParameter("query", title);
        RequestParameter inclAdultParam = new RequestParameter("include_adult", "false");
        String json = restClient.get(RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_SEARCH, METHOD_MOVIE)
                .withRequestParams(restClient.defaultPagedRequestParams())
                .withRequestParams(searchParam, inclAdultParam)
                .getRequestUrl());
        return restClient.parse(json);
    }
}
