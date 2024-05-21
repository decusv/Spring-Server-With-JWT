package com.spring_one.webSerrver.query;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

/**
 * Configuration class for initializing Query data.
 */
@Configuration
public class QueryConfig {

    /**
     * Bean that runs immediately after the Spring Boot application starts.
     * This CommandLineRunner implementation saves a sample Query entity to the database.
     *
     * @param queryRepository the repository used to save the sample Query entity.
     * @return a CommandLineRunner that saves a sample Query entity.
     */
    @Bean
    CommandLineRunner commandLineRunner(QueryRepository queryRepository) {
        return (args) -> {
               Query sampleQuery = new Query(
                       1L,
                       "OpenAI",
                       "ChatGPT-3.5",
                       1024,
                       1024
               );
                queryRepository.saveAll(List.of(sampleQuery));

        };
    }
}
