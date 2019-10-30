package com.w1sh.watcher.connections;

import com.w1sh.watcher.clients.common.RateLimiter;
import com.w1sh.watcher.configurations.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;

@Component
public class TmdbConnection {

    private final Logger logger = LoggerFactory.getLogger(TmdbConnection.class);
    private final PropertiesConfiguration propertiesConfiguration;
    private final RateLimiter rateLimiter;
    private final HttpClientConnection connection;

    public static final String TMDB_SEARCH_MOVIE =
            "https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&page=1&include_adult=false&query=";
    public static final String TMDB_MOVIES_GENRES_LIST =
            "https://api.themoviedb.org/3/genre/movie/list?api_key=<<api_key>>&language=en-US";
    public static final String TMDB_TV_GENRES_LIST =
            "https://api.themoviedb.org/3/genre/tv/list?api_key=<<api_key>>&language=en-US";

    public TmdbConnection(PropertiesConfiguration propertiesConfiguration, RateLimiter rateLimiter,
                          HttpClientConnection connection) {
        this.propertiesConfiguration = propertiesConfiguration;
        this.rateLimiter = rateLimiter;
        this.connection = connection;
    }

    public String searchMoviesByTitle(String title){
        String requestUrl = TMDB_SEARCH_MOVIE.replace("<<api_key>>", propertiesConfiguration.getTmdbKey())
                .concat(title);
        return request(requestUrl);
    }

    public String getAllMovieGenres(){
        String requestUrl = TMDB_MOVIES_GENRES_LIST.replace("<<api_key>>", propertiesConfiguration.getTmdbKey());
        return request(requestUrl);
    }

    public String getAllTVGenres(){
        String requestUrl = TMDB_TV_GENRES_LIST.replace("<<api_key>>", propertiesConfiguration.getTmdbKey());
        return request(requestUrl);
    }

    private String request(String requestUrl){
        try {
            Instant start = Instant.now();
            HttpResponse response = connection.get(requestUrl);
            Instant end = Instant.now();
            logger.info("Completed request in {} ms", Duration.between(start, end).toMillis());

            if (response.statusCode() == 200 || response.statusCode() == 201){
                rateLimiter.success(response.headers());
                return (String) response.body();
            } else {
                rateLimiter.failed(response.headers());
            }
        } catch (IOException e) {
            logger.error("An error occurred when receiving data", e);
        } catch (InterruptedException e) {
            logger.error("The connection was interrupted", e);
        }
        return "";
    }
}
