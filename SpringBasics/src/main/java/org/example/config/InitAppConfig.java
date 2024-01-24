package org.example.config;

import org.example.Contact;
import org.example.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeSet;

@Configuration
@PropertySource("classpath:application-init.properties")
@Profile("init")
public class InitAppConfig {

    @Value("${app.contact-data-file.path}")
    private String contactDataFilePath;
    private ContactService contactService;

    public InitAppConfig(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostConstruct
    public void initContacts() throws IOException {
        TreeSet<Contact>contacts = contactService.getContacts();
        List<String> dataList = Files.readAllLines(Paths.get(contactDataFilePath));
        dataList.forEach(s -> contacts.add(contactService.addNewContact(s, "; ")));
        contactService.setContacts(contacts);
    }
}
