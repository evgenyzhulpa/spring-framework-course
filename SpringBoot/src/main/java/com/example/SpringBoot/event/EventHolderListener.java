package com.example.SpringBoot.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHolderListener {
    @EventListener
    public void listenEvent(EventHolder holder) {
        System.out.println(holder.getInfo());
    }

}
