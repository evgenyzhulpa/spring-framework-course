package com.example.SpringBoot.config;

import com.example.SpringBoot.StudentStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@ConditionalOnProperty("app.generation.student")
@RequiredArgsConstructor
public class StudentAccountingConfiguration {

    private final StudentStorage studentStorage;

    @EventListener(ApplicationStartedEvent.class)
    public void generateStudents() {
        studentStorage.generate();
    }
}
