package com.ironhack.pharmacyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication  @EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableJpaRepositories("com.ironhack.pharmacyapi.repository")
@EntityScan("com.ironhack.pharmacyapi.model")
public class PharmacyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmacyApiApplication.class, args);
    }

}
