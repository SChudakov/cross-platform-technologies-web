package com.chudakov.crossplatformtechnologiesweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrossPlatformTechnologiesWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrossPlatformTechnologiesWebApplication.class, args);
        System.out.println("Access the application via http://localhost:8080");
    }
}
