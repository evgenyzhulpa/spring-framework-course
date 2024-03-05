package com.example.SpringWebApp.service;

import com.example.SpringWebApp.dto.Contact;
import com.example.SpringWebApp.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> findAll() {
        log.debug("Running a search on all contacts...");
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> findById(String id) {
        log.debug("Running a search on contact by ID" + id + "...");
        return contactRepository.findById(UUID.fromString(id));
    }

    @Override
    public Contact createOrUpdate(Contact contact) {
        if (contact.getId() == null) {
            return createContact(contact);
        }
        return updateContact(contact);
    }

    public Contact createContact(Contact contact) {
        log.debug("Creating a new contact...");
        Contact addedContact = new Contact();
        addedContact.setId(UUID.randomUUID());
        setContactAttributes(addedContact, contact);
        return contactRepository.save(addedContact);
    }

    public Contact setContactAttributes(Contact existedContact, Contact contact) {
        existedContact.setFirstName(contact.getFirstName());
        existedContact.setLastName(contact.getLastName());
        existedContact.setEmail(contact.getEmail());
        existedContact.setPhone(contact.getPhone());
        return existedContact;
    }

    public Contact updateContact(Contact contact) {
        log.debug("Updating a contact...");
        Contact existedContact = contactRepository.findById(contact.getId()).orElse(null);
        if (existedContact != null) {
            setContactAttributes(existedContact, contact);
        }
        return contactRepository.save(existedContact);
    }

    @Override
    public void delete(String id) {
        log.debug("Deleting a contact...");
        if (id == null && id.isBlank()) {
            return;
        }
        contactRepository.delete(UUID.fromString(id));
    }
}
