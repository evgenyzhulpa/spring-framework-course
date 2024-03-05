package com.example.SpringWebApp.controller;

import com.example.SpringWebApp.dto.Contact;
import com.example.SpringWebApp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Contact> contacts =  contactService.findAll();
        model.addAttribute("contacts", contacts);
        return "index";
    }

    @GetMapping("/contacts/create")
    public String showCreatingForm(Model model) {
       model.addAttribute("contact", new Contact());
       return "/edit";
    }

    @GetMapping("/contacts/edit/{id}")
    public String showEditingForm(@PathVariable("id") String contactId, Model model) {
        Optional<Contact> optional = contactService.findById(contactId);
        if (optional.isPresent()) {
            model.addAttribute("contact", optional.get());
            return "/edit";
        }
        return "redirect:/";
    }

    @PostMapping("/contacts/save")
    public String createOrUpdate(@ModelAttribute Contact contact)
    {
        contactService.createOrUpdate(contact);
        return "redirect:/";
    }

    @GetMapping("/contacts/delete/{id}")
    public String delete(@PathVariable("id") String contactId) {
        contactService.delete(contactId);
        return "redirect:/";
    }
}
