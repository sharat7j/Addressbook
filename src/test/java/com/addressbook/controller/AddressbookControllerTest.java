package com.addressbook.controller;

import com.addressbook.exceptions.MissingDataException;
import com.addressbook.exceptions.UniquenessException;
import com.addressbook.model.Contact;
import com.addressbook.request.ContactRequest;
import com.addressbook.service.ContactService;
import com.addressbook.util.Helpers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by sharatjagannath on 7/26/17.
 */
public class AddressbookControllerTest {


    @Mock
    ContactService contactService;

    @InjectMocks
    AddressBookController AddressBookController = new AddressBookController(contactService);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = MissingDataException.class)
    public void testcreateContactException() throws Exception, MissingDataException {
        when(contactService.createContact(any(Contact.class))).thenThrow(new MissingDataException("phone number is missing"));
        AddressBookController.createContact(new ContactRequest());
    }

    @Test(expected = UniquenessException.class)
    public void testcreateContactExistingConflict() throws Exception, MissingDataException {
        when(contactService.createContact(any(Contact.class))).thenThrow(new UniquenessException("Contact with phone  already exists"));
        AddressBookController.createContact(new ContactRequest());

    }

    @Test
    public void testDeleteContact() throws Exception {
        doNothing().when(contactService).deleteContact(any(Long.class));
        AddressBookController.deleteContact(Long.parseLong("1"));
    }

    @Test
    public void testContactList() throws Exception {
        List<Contact> ContactList = new ArrayList<>();
        ContactList.add(Helpers.churnContact());
        ContactList.add(Helpers.churnContact());
        ContactList.add(Helpers.churnContact());
        PageImpl<Contact> ContactPage = new PageImpl<>(ContactList);

        when(contactService.listContacts(any(Pageable.class))).thenReturn(ContactPage);
        assertNotNull(AddressBookController.listContacts(null));
        assertEquals(AddressBookController.listContacts(null), ContactPage);

    }

    @Test
    public void testContactListByState() throws Exception {
        List<Contact> ContactList = new ArrayList<>();
        ContactList.add(Helpers.churnContact());
        ContactList.add(Helpers.churnContact());
        ContactList.add(Helpers.churnContact());
        PageImpl<Contact> ContactPage = new PageImpl<>(ContactList);

        when(contactService.listContactsInState(any(String.class),any(Pageable.class))).thenReturn(ContactPage);
        assertNotNull(AddressBookController.listContactsInState(null,null));
        assertEquals(AddressBookController.listContactsInState(null,null), ContactPage);

    }

    @Test(expected = MissingDataException.class)
    public void testupdateContactNotExistsConflict() throws Exception, MissingDataException {
        when(contactService.updateContact(any(Contact.class))).thenThrow(new MissingDataException("Contact with phone  already exists"));
        AddressBookController.updateContact(new ContactRequest());

    }
}
