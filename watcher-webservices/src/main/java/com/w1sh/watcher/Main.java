package com.w1sh.watcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.w1sh.watcher", exclude = {SecurityAutoConfiguration.class})
@EntityScan (basePackages = {"com.w1sh.watcher"} )
@EnableJpaRepositories (basePackages = {"com.w1sh.watcher"})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
