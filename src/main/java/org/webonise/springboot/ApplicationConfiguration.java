package org.webonise.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public HashSet<UUID> hashSet() {
        return new HashSet<UUID>();
    }
}
