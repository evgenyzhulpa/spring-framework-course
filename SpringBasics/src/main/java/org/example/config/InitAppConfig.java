package org.example.config;

import org.example.ContactInitializer;
import org.example.FileContactInitializer;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application-init.properties")
@Profile("init")
public class InitAppConfig {
    @Bean
    public ContactInitializer contactInitializer() {
        return new FileContactInitializer();
    }
}
