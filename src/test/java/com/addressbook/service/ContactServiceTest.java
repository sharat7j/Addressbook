package com.addressbook.service;

import com.addressbook.exceptions.MissingDataException;
import com.addressbook.exceptions.UniquenessException;
import com.addressbook.model.Contact;
import com.addressbook.repository.ContactRepository;
import com.addressbook.util.Helpers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by sharatjagannath on 7/26/17.
 */
public class ContactServiceTest {


    @Mock
    ContactRepository contactRepository;


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    ContactService contactService = new ContactService(contactRepository);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testListContacts() throws Exception {
        when(contactRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        assertEquals(contactService.listContacts(null).getTotalElements(), 0);
    }

    @Test(expected = MissingDataException.class)
    public void testCreateContactMissingException() throws Exception, MissingDataException {
        contactService.createContact(null);
    }


    @Test
    public void testCreateRollOut() throws Exception, MissingDataException {
        Contact contact = (Helpers.churnContact());
        when(contactRepository.phoneExists(any(String.class))).thenReturn(null);
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);
        Contact result = contactService.createContact(contact);
        assertEquals(result, contact);
    }

    @Test
    public void testDeleteRollOuts() throws Exception {
        doNothing().when(contactRepository).deleteContact(any(Long.class));
        contactService.deleteContact(Long.parseLong("1"));
    }

}
