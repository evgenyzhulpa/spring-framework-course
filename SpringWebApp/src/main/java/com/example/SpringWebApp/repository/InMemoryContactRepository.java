package com.example.SpringWebApp.repository;

import com.example.SpringWebApp.dto.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryContactRepository implements ContactRepository {

    private final List<Contact> contacts;

    public InMemoryContactRepository(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public List<Contact> findAll() {
        return contacts;
    }

    @Override
    public Optional<Contact> findById(UUID id) {
        return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst();
    }

    @Override
    public Contact save(Contact contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
        }
        return contact;
    }

    @Override
    public void delete(UUID id) {
        Contact existedContact = findById(id).orElse(null);
        if (existedContact != null) {
            contacts.remove(existedContact);
        }
    }
}
