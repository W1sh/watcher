package com.w1sh.watcher;

import com.w1sh.watcher.utils.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class HttpClientConnection {

    private final Logger logger = LoggerFactory.getLogger(HttpClientConnection.class);
    private final PropertiesConfiguration propertiesConfiguration;
    private final HttpClient client;
    private final RateLimiter rateLimiter;

    public static final String TMDB_SEARCH_MOVIE =
            "https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&page=1&include_adult=false&query=Marvel";

    public HttpClientConnection(PropertiesConfiguration propertiesConfiguration, RateLimiter rateLimiter) {
        this.propertiesConfiguration = propertiesConfiguration;
        this.rateLimiter = rateLimiter;

        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .proxy(ProxySelector.getDefault())
                .build();
    }

    public String get(String url){
        String requestUrl = url.replace("<<api_key>>", propertiesConfiguration.getTmdbKey());

        var requestMovies = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .timeout(Duration.ofSeconds(3))
                .header("Content-Type", "application/json")
                .build();

        try {
            var response = this.client.send(requestMovies, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200 || response.statusCode() == 201){
                rateLimiter.success(response.headers());
                return response.body();
            } else {
                logger.error("Failed to retrieve response. Status: {}", response.statusCode());
                rateLimiter.failed(response.headers());
            }
        } catch (IOException e) {
            logger.error("", e);
        } catch (InterruptedException e) {
            logger.error("", e);
        }
        return "";
    }
}
