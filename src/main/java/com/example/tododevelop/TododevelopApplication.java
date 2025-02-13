package com.example.tododevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TododevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TododevelopApplication.class, args);
    }

}
