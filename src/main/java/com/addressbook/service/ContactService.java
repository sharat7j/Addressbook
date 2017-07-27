package com.addressbook.service;

import com.addressbook.controller.AddressBookController;
import com.addressbook.exceptions.MissingDataException;
import com.addressbook.exceptions.UniquenessException;
import com.addressbook.repository.ContactRepository;
import com.addressbook.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by sharatjagannath on 7/24/17.
 */

@Service
public class ContactService {
    private ContactRepository contactRepository;
    final static Logger logger = LoggerFactory.getLogger(AddressBookController.class);
    @Autowired
    public ContactService(ContactRepository repository) {
        this.contactRepository = repository;

    }
    public Contact createContact(Contact contact) throws UniquenessException, MissingDataException {
        if(contact == null || contact.getPhoneNumber() == null) {

            throw new MissingDataException("Required attribute \'phone number\' is missing");
        }


        return contactRepository.save(contact);
    }

    public Page<Contact> listContacts(Pageable pageable){

        return contactRepository.findAll(pageable);
    }

    public Page<Contact> listContactsInState(String state, Pageable pageable){

        return contactRepository.contactsByState(state,pageable);
    }
    public Contact updateContact(Contact contact) throws  MissingDataException {
        if(contact == null || contact.getPhoneNumber() == null) {

            throw new MissingDataException("Required  attribute \'phone number\' is missing");
        }

        Contact updateContact = contactRepository.findOne(contact.getId());
        updateContact.setPhoneNumber(contact.getPhoneNumber());
        updateContact.setEmail(contact.getEmail());
        updateContact.setFirstName(contact.getFirstName());
        updateContact.setLastName(contact.getLastName());
        updateContact.setState(contact.getState());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        try {
            if(contact.getLastContactDate()!=null) {
                updateContact.setLastContactDate(df.parse(contact.getLastContactDate()));
            }
        } catch (ParseException e) {
           logger.error("Error parsing date while updating");
        }

        return contactRepository.save(updateContact);
    }

    public void deleteContact(Long id) {

        contactRepository.deleteContact(id);
    }


}
