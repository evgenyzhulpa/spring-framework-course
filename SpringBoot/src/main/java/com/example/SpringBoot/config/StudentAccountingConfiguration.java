package com.example.SpringBoot.config;

import com.example.SpringBoot.StudentGenerator;
import com.example.SpringBoot.StudentStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty("app.generation.student")
@RequiredArgsConstructor
public class StudentAccountingConfiguration {

    private final StudentStorage studentStorage;

    @Bean
    public StudentGenerator studentGenerator() {
        return new StudentGenerator(studentStorage);
    }
}
