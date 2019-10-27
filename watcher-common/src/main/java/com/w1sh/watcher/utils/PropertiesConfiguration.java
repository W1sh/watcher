package com.w1sh.watcher.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource ("classpath:application.properties")
public class PropertiesConfiguration {

    @Value ("${tmdb.key}")
    private String tmdbKey;

    public String getTmdbKey() {
        return tmdbKey;
    }
}
