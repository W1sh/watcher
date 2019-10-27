package com.w1sh.watcher.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.w1sh.watcher")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
