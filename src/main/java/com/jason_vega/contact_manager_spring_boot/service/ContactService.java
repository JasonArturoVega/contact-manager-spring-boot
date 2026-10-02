package com.jason_vega.contact_manager_spring_boot.service;

import com.jason_vega.contact_manager_spring_boot.model.Contact;
import com.jason_vega.contact_manager_spring_boot.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service //Logic, validations, rules
public class ContactService {

    public ContactService(ContactRepository contactRepository)
    {
        this.contactRepository = contactRepository;
    }

    //Save access to repository
    private final ContactRepository contactRepository;

    //Add a new contact
    public Contact createContact(Contact contactToAdd)
    {
        //The contact needs a phone number
        if(contactToAdd.getPhoneNumber() == null || contactToAdd.getPhoneNumber().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Phone number required.");

        //It needs to be a valid Email
        validateEmail(contactToAdd.getEmail());

        return contactRepository.save(contactToAdd);
    }

    //Obtain all the contacts
    public List<Contact> getAllContacts()
    {
        return contactRepository.findAll();
    }

    //Obtain one contact by id
    public Contact getContactById(Long id)
    {
        return contactRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact not found."));
    }

    //Obtain one contact by phone number
    public Contact getContactByNumber(String phoneNumber)
    {
        if(phoneNumber == null || phoneNumber.isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Number required.");

        //Calls the function of the repository "Optional<Contact> findByPhoneNumber(String phoneNumber);" to check
        return contactRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact not found."));
    }

    //Obtain one contact by name
    public Contact getContactByName(String name)
    {
        if(name == null || name.isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name required.");

        //Calls the function of the repository "Optional<Contact> findByNane(String name);" to check
        return contactRepository.findByNameIgnoreCase(name).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact not found."));
    }

    //Obtain one contact by email
    public Contact getContactByEmail(String email)
    {
        if(email == null || email.isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email required.");

        //Calls the function of the repository "Optional<Contact> findByEmail(String email);" to check
        return contactRepository.findByEmailIgnoreCase(email).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact not found."));
    }

    //Erase a contact
    public void deleteContact(long id)
    {
        if(!contactRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found.");

        contactRepository.deleteById(id);
    }

    //Edit an existing contact
    public Contact editContact(Long id, Contact editedContact)
    {
        //The contact needs a phone number
        if(editedContact.getPhoneNumber() == null || editedContact.getPhoneNumber().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Phone number required.");

        //It needs to be a valid Email
        validateEmail(editedContact.getEmail());

        Contact foundContact = getContactById(id);

        foundContact.setName(editedContact.getName());
        foundContact.setEmail(editedContact.getEmail());
        foundContact.setPhoneNumber(editedContact.getPhoneNumber());

        return contactRepository.save(foundContact);
    }

    private void validateEmail(String email)
    {
        //The Email is optional
        //Null goes first, second goes if is blank
        if(email == null || email.isBlank())
            return;

        if(!email.contains("@"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email invalid.");

    }
}
