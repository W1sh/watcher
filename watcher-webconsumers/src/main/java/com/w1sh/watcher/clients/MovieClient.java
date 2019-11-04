package com.w1sh.watcher.clients;

import com.w1sh.watcher.limiters.RateLimiter;
import com.w1sh.watcher.clients.common.RequestBuilder;
import com.w1sh.watcher.clients.common.RequestParameter;
import com.w1sh.watcher.utils.ClientUtils;
import com.w1sh.watcher.connections.HttpClientConnection;
import com.w1sh.watcher.dtos.MovieDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.w1sh.watcher.constants.TmdbConstants.*;

@Component
public class MovieClient {

    private static final Logger logger = LoggerFactory.getLogger(MovieClient.class);

    private final RateLimiter rateLimiter;
    private final HttpClientConnection connection;
    private final ClientUtils utils;

    public MovieClient(RateLimiter rateLimiter, HttpClientConnection connection, ClientUtils utils) {
        this.rateLimiter = rateLimiter;
        this.connection = connection;
        this.utils = utils;
    }

    public List<MovieDTO> findPopular(){
        logger.info("Preparing to query TMDb API for popular movies");
        String requestUrl = RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_MOVIE, METHOD_POPULAR)
                .withRequestParams(utils.defaultPagedRequestParams())
                .getRequestUrl();
        String json = connection.get(requestUrl, rateLimiter);
        return utils.parse(json);
    }

    public List<MovieDTO> findNowPlaying(){
        logger.info("Preparing to query TMDb API for now playing movies");
        String requestUrl = RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_MOVIE, METHOD_NOW_PLAYING)
                .withRequestParams(utils.defaultPagedRequestParams())
                .getRequestUrl();
        String json = connection.get(requestUrl, rateLimiter);
        return utils.parse(json);
    }

    public List<MovieDTO> findTopRated(){
        logger.info("Preparing to query TMDb API for top rated movies");
        String requestUrl = RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_MOVIE, METHOD_TOP_RATED)
                .withRequestParams(utils.defaultPagedRequestParams())
                .getRequestUrl();
        String json = connection.get(requestUrl, rateLimiter);
        return utils.parse(json);
    }

    public List<MovieDTO> findUpcoming(){
        logger.info("Preparing to query TMDb API for upcoming movies");
        String requestUrl = RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_MOVIE, METHOD_UPCOMING)
                .withRequestParams(utils.defaultPagedRequestParams())
                .getRequestUrl();
        String json = connection.get(requestUrl, rateLimiter);
        return utils.parse(json);
    }

    public List<MovieDTO> findByTitle(String title){
        logger.info("Preparing to query TMDb API for movies with title containing \"{}\"", title);
        RequestParameter searchParam = new RequestParameter("query", title);
        RequestParameter inclAdultParam = new RequestParameter("include_adult", "false");
        String requestUrl = RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_SEARCH, METHOD_MOVIE)
                .withRequestParams(utils.defaultPagedRequestParams())
                .withRequestParams(searchParam, inclAdultParam)
                .getRequestUrl();
        String json = connection.get(requestUrl, rateLimiter);
        return utils.parse(json);
    }
}