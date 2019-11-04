package com.w1sh.watcher.clients;

import com.w1sh.watcher.limiters.RateLimiter;
import com.w1sh.watcher.clients.common.RequestBuilder;
import com.w1sh.watcher.utils.ClientUtils;
import com.w1sh.watcher.connections.HttpClientConnection;
import com.w1sh.watcher.dtos.GenreDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.w1sh.watcher.constants.TmdbConstants.*;

@Component
public class GenreClient {

    private static final Logger logger = LoggerFactory.getLogger(MovieClient.class);

    private final RateLimiter rateLimiter;
    private final HttpClientConnection connection;
    private final ClientUtils utils;

    public GenreClient(RateLimiter rateLimiter, HttpClientConnection connection, ClientUtils utils) {
        this.rateLimiter = rateLimiter;
        this.connection = connection;
        this.utils = utils;
    }

    public List<GenreDTO> getAllMovieGenres(){
        logger.info("Preparing to query TMDb API for all movie genres");
        String requestUrl = RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_GENRE, METHOD_MOVIE, METHOD_LIST)
                .withRequestParams(utils.defaultRequestParams())
                .getRequestUrl();
        String json = connection.get(requestUrl, rateLimiter);
        return utils.parse(json);
    }

    public List<GenreDTO> getAllTVGenres(){
        logger.info("Preparing to query TMDb API for all tv genres");
        String requestUrl = RequestBuilder.withBaseUrl(TMDB_API_BASE)
                .withMethods(METHOD_GENRE, METHOD_TV, METHOD_LIST)
                .withRequestParams(utils.defaultRequestParams())
                .getRequestUrl();
        String json = connection.get(requestUrl, rateLimiter);
        return utils.parse(json);
    }
}
