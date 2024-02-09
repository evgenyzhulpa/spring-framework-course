package com.example.SpringBoot.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventHolder extends ApplicationEvent {
    private final String info;

    public EventHolder(Object source, String info) {
        super(source);
        this.info = info;
    }
}
