package org.example;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

public class DefaultContactInitializer implements ContactInitializer {

    @Override
    public TreeSet<Contact> init() {
        return new TreeSet<>();
    }
}
