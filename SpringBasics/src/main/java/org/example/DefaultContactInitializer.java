package org.example;

import java.util.TreeSet;

public class DefaultContactInitializer implements ContactInitializer {

    @Override
    public TreeSet<Contact> init() {
        return new TreeSet<>();
    }
}
