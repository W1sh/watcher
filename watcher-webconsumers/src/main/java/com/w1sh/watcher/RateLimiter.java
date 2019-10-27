package com.w1sh.watcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RateLimiter {

    private final Logger logger = LoggerFactory.getLogger(RateLimiter.class);

    public static final int RATE_LIMIT = 40;
    public static final int RATE_LIMIT_DURATION = 10;

    //public static final Duration RATE_COOLDOWN = Duration.ofSeconds(RATE_LIMIT_DURATION);

    private int currentRate;

    public void success(){

    }

    public void failed(){

    }

    public void refresh(){

    }
}
