package com.jason_vega.contact_manager_spring_boot.repository;

import com.jason_vega.contact_manager_spring_boot.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    //Allow you to create a repository with Contacts and a number (ID)
    //This already have methods like save(), findAll(), findById(), deleteById() and more

    //Search for a contact with a specific number, returns empty optional if the contact does NOT exist
    Optional<Contact> findByPhoneNumber(String phoneNumber);

    //Search for a contact with a specific name, returns empty optional if the contact does NOT exist
    Optional<Contact> findByNameIgnoreCase(String name);

    //Search for a contact with a specific email, returns empty optional if the contact does NOT exist
    Optional<Contact> findByEmailIgnoreCase(String email);
}
