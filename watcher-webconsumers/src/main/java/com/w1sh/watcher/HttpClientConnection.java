package com.w1sh.watcher;

import com.w1sh.watcher.utils.PropertiesConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

@Component
public class HttpClientConnection {

    private final ModelMapper modelMapper;
    private final PropertiesConfiguration propertiesConfiguration;

    public static final String TMDB_SEARCH_MOVIE =
            "https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&page=1&include_adult=false";

    public HttpClientConnection(ModelMapper modelMapper, PropertiesConfiguration propertiesConfiguration) {
        this.modelMapper = modelMapper;
        this.propertiesConfiguration = propertiesConfiguration;
    }

    public static String get(String url){
        final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // default
                .followRedirects(HttpClient.Redirect.NORMAL) // Always redirect, except from HTTPS URLs to HTTP URLs.
                .proxy(ProxySelector.getDefault())
                .build();

        var requestMovies = HttpRequest.newBuilder()
                .uri(URI.create(TMDB_SEARCH_MOVIE))
                .timeout(Duration.ofSeconds(10))
                .header("Content-Type", "application/json")
                .build();
        return "";
    }
}
