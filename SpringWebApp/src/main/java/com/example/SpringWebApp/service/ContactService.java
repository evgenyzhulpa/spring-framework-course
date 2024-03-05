package com.example.SpringWebApp.service;

import com.example.SpringWebApp.dto.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<Contact> findAll();
    Optional<Contact> findById(String id);
    Contact createOrUpdate(Contact contact);
    void delete(String id);
}
