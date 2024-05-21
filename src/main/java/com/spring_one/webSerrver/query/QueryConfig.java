package com.spring_one.webSerrver.query;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
               Student joe = new Student(
                        10L,
                        "Joe",
                        "Joe.Frisk@gmail.com",
                        LocalDate.of(1999, Month.SEPTEMBER,12)
                );
                Student peter = new Student(
                        10L,
                        "Peter",
                        "Peter.Frisk@gmail.com",
                        LocalDate.of(1999, Month.SEPTEMBER,22)
                );

                studentRepository.saveAll(List.of(joe,peter));

        };
    }
}
