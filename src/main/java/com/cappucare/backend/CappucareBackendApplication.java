package com.cappucare.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.cappucare.backend")
public class CappucareBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CappucareBackendApplication.class, args);
    }
}
