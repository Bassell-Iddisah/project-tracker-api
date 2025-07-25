package com.gentleninja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjectTrackerApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectTrackerApplication.class, args);
    }
}
