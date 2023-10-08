package com.example.lab72;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Lab72Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab72Application.class, args);
        System.out.println("Hello Moon");
    }

}
