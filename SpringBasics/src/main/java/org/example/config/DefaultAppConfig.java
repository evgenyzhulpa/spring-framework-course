package org.example.config;

import org.example.ContactInitializer;
import org.example.DefaultContactInitializer;
import org.springframework.context.annotation.*;

@ComponentScan("org.example")
@Configuration
@PropertySource("classpath:application.properties")
public class DefaultAppConfig {
    @Bean
    @Profile("!init")
    public ContactInitializer contactInitializer() {
        return new DefaultContactInitializer();
    }

}
