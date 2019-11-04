package com.w1sh.watcher.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource ("classpath:application.properties")
public class PropertiesConfiguration {

    @Value ("${tmdb.key}")
    @Getter
    private String tmdbKey;

}
