package com.w1sh.watcher.connections;

import com.w1sh.watcher.limiters.RateLimiter;
import lombok.extern.slf4j.Slf4j;
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
import java.time.Instant;

@Slf4j
@Component
public class HttpClientConnection {

    private final HttpClient client;

    public HttpClientConnection() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .proxy(ProxySelector.getDefault())
                .build();
    }

    public String get(String requestUrl, RateLimiter rateLimiter){
        try {
            Instant start = Instant.now();
            HttpResponse response = get(requestUrl);
            Instant end = Instant.now();
            log.info("Completed request in {} ms", Duration.between(start, end).toMillis());

            if (response.statusCode() == 200 || response.statusCode() == 201){
                rateLimiter.success(response.headers());
                return (String) response.body();
            } else {
                rateLimiter.failed(response.headers());
            }
        } catch (IOException e) {
            log.error("An error occurred when receiving data", e);
        } catch (InterruptedException e) {
            log.error("The connection was interrupted", e);
        }
        return "";
    }

    private HttpResponse get(String url) throws IOException, InterruptedException {
        log.info("Creating http request...");
        var requestMovies = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(3))
                .header("Content-Type", "application/json")
                .build();

        return this.client.send(requestMovies, HttpResponse.BodyHandlers.ofString());
    }
}
