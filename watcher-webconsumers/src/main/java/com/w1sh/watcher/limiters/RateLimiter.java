package com.w1sh.watcher.limiters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.http.HttpHeaders;
import java.util.NoSuchElementException;

@Component
public class RateLimiter {

    private final Logger logger = LoggerFactory.getLogger(RateLimiter.class);

    public static final int RATE_LIMIT = 40;
    public static final int RATE_LIMIT_DURATION = 10;

    private int currentRate;

    public void success(HttpHeaders headers){
        try {
            this.currentRate = RATE_LIMIT - Integer.parseInt(headers.firstValue("X-RateLimit-Remaining").orElseThrow());
            logger.info("Request successful. Current rate {}", currentRate);
        } catch (NoSuchElementException e) {
            logger.error("No header field found", e);
        }
    }

    public void failed(HttpHeaders headers){
        logger.info("Request failed. Current rate {}", currentRate);
    }
}
