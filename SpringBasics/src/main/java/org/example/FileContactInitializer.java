package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeSet;

public class FileContactInitializer implements ContactInitializer{
    @Value("${app.contact-data-file.path}")
    private String contactDataFilePath;

    @Override
    public TreeSet<Contact> init() throws IOException {
        TreeSet<Contact> contacts = new TreeSet<>();
        List<String> dataList = Files.readAllLines(Paths.get(contactDataFilePath));
        dataList.forEach(s -> contacts.add(addNewContact(s, "; ")));
        return contacts;
    }
}
