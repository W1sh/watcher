package com.w1sh.watcher.connections;

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
    private final HttpClient client;

    public HttpClientConnection() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .proxy(ProxySelector.getDefault())
                .build();
    }

    public HttpResponse get(String url) throws IOException, InterruptedException {
        logger.info("Creating http request...");
        var requestMovies = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(3))
                .header("Content-Type", "application/json")
                .build();

        return this.client.send(requestMovies, HttpResponse.BodyHandlers.ofString());
    }
}
