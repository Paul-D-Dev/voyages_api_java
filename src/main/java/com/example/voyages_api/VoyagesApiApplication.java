package com.example.voyages_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VoyagesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoyagesApiApplication.class, args);
    }

}
