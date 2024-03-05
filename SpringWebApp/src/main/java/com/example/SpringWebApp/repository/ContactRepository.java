package com.example.SpringWebApp.repository;

import com.example.SpringWebApp.dto.Contact;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepository {
    List<Contact> findAll();
    Optional<Contact> findById(UUID id);
    Contact save(Contact contact);
    void delete(UUID id);
}
