package com.w1sh.watcher.limiters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.http.HttpHeaders;
import java.util.NoSuchElementException;

@Slf4j
@Component
public class RateLimiter {

    public static final int RATE_LIMIT = 40;
    public static final int RATE_LIMIT_DURATION = 10;

    private int currentRate;

    public void success(HttpHeaders headers){
        try {
            this.currentRate = RATE_LIMIT - Integer.parseInt(headers.firstValue("X-RateLimit-Remaining").orElseThrow());
            log.info("Request successful. Current rate {}", currentRate);
        } catch (NoSuchElementException e) {
            log.error("No header field found", e);
        }
    }

    public void failed(HttpHeaders headers){
        log.info("Request failed. Current rate {}", currentRate);
    }
}
