package com.gentleninja;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Base64;

@SpringBootApplication
@EnableCaching
public class ProjectTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectTrackerApplication.class, args);
    }
}
