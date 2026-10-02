package com.jason_vega.contact_manager_spring_boot.controller;

import com.jason_vega.contact_manager_spring_boot.model.Contact;
import com.jason_vega.contact_manager_spring_boot.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController //End points: POST, GET, DELETE and more
@RequestMapping("/contacts")
public class ContactController {

    public ContactController(ContactService contactService)
    {
        this.contactService = contactService;
    }

    //Access to the service
    private final ContactService contactService;

    //Call the "createContact" function of the service with POST
    @PostMapping
    public Contact create(@RequestBody Contact contactCreated)
    {
        return contactService.createContact(contactCreated);
    }

    //Call the "getAllContacts" function of the service with GET
    @GetMapping
    public List<Contact> getAll()
    {
        return contactService.getAllContacts();
    }

    //Call the get getContactById function of the service with GET and an id
    @GetMapping("/by-id/{id}")
    public Contact getOneContactById(@PathVariable Long id)
    {
        return contactService.getContactById(id);
    }

    //GET /contacts/search?phoneNumber=912345678
    //GET /contacts/search?name=Ana
    //GET /contacts/search?email=ana@mail.com
    @GetMapping("/search")
    public Contact searchContact(@RequestParam(required = false) String phoneNumber,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String email)
    {
        //Call the get getContactByNumber function of the service with GET and a phone number
        if(phoneNumber != null && !phoneNumber.isBlank())
            return contactService.getContactByNumber(phoneNumber);

        //Call the get getContactByName function of the service with GET and a name
        if(name != null && !name.isBlank())
            return contactService.getContactByName(name);

        //Call the get getContactByEmail function of the service with GET and an email
        if(email != null && !email.isBlank())
            return contactService.getContactByEmail(email);

        //Return nothing is no filter
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide phoneNumber, name or email");
    }

    //Call the deleteContact function of the service with DELETE
    @DeleteMapping("/by-id/{id}")
    public String delete(@PathVariable Long id)
    {
        contactService.deleteContact(id);
        return "Contact id: " + id + " deleted.";
    }

    //Call the editContact function of the service with PUT
    @PutMapping("/by-id/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact)
    {
        return contactService.editContact(id, contact);
    }
}
