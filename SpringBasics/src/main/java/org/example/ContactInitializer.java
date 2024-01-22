package org.example;

import java.io.IOException;
import java.util.TreeSet;

public interface ContactInitializer {
    TreeSet<Contact> init() throws IOException;
    default Contact addNewContact(String data, String regex) {
        String[] dataArray = data.split(regex);
        return new Contact(dataArray[0], dataArray[1], dataArray[2]);
    }
}
