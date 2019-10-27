package com.w1sh.watcher;

import com.w1sh.watcher.utils.PropertiesConfiguration;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    private final PropertiesConfiguration propertiesConfiguration;
    private final HttpClient client;

    public static final String TMDB_SEARCH_MOVIE =
            "https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&page=1&include_adult=false&query=Marvel";

    public HttpClientConnection(ModelMapper modelMapper, PropertiesConfiguration propertiesConfiguration) {
        this.modelMapper = modelMapper;
        this.propertiesConfiguration = propertiesConfiguration;

        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // default
                .followRedirects(HttpClient.Redirect.NORMAL) // Always redirect, except from HTTPS URLs to HTTP URLs.
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
                logger.info("Success!");
                System.out.println(response.body());
                logger.info("Requests remaining: {}", response.headers().firstValue("X-RateLimit-Remaining").orElseThrow());
            } else {
                logger.error("Failed to retrieve response. Status: " + response.statusCode());
            }
        } catch (IOException e) {
            logger.error("", e);
        } catch (InterruptedException e) {
            logger.error("", e);
        }
        return "";
    }
}
