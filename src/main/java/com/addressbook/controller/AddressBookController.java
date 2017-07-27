package com.addressbook.controller;

import com.addressbook.exceptions.MissingDataException;
import com.addressbook.service.ContactService;
import io.swagger.annotations.*;
import com.addressbook.model.Contact;
import com.addressbook.request.ContactRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.addressbook.exceptions.UniquenessException;

import java.util.List;


/**
 * Created by sharatjagannath on 7/24/17.
 */
@RestController
@Api(value = "Contacts Controller", tags = "Contacts Controller", description = "Interface definitions that support all " +
        "operations required to manage Contacts")
@RequestMapping("/v1")
public class AddressBookController {
    private ContactService contactService;
    final static Logger logger = LoggerFactory.getLogger(AddressBookController.class);
    @Autowired
    public AddressBookController(ContactService contactService) {
        this.contactService = contactService;
    }
    /**
     * Create a contact in address
     *
     * @param contactRequest The contact request object serialized into a JSON on the post request.
     * @return The Concat object confirming that its stored in the persistence store.
     * @throws .UniquenessException  Thrown if the contact with a similar email already exists
     * @throws .MissingDataException  Thrown if the contact with a similar email already exists
     */
    @ApiOperation(value = "Create a new contact", notes = "Create a new contact out that is unique by email")
    @RequestMapping(value = "/contact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Contact.class),
            @ApiResponse(code = 400, message = "Bad request if either a contact already exists with the same phone number  " +
                    "if the name attribute is empty", response = String.class)
    })
    public Contact createContact(@ApiParam(value = "ContactRequest payload as JSON for creation of a Contact")
                                 @RequestBody ContactRequest contactRequest) throws UniquenessException,MissingDataException {
        return contactService.createContact(Contact.getContactFromRequest(contactRequest));
    }

    /**
     * List all contacts in the system
     *
     * @param pageable Pagination object that contains page attributes to filter on listing
     * @return The list of contact objects available in the system
     */
    @ApiOperation(value = "list all Contacts in the system", notes = "Paginated list of all Contacts in the system",
            response = Contact.class, responseContainer = "List")
    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    @ApiResponse(code = 200, message = "Paginated list of Contacts")
    public Page<Contact> listContacts(Pageable pageable) {
        // cascaded delete of all jobs that belong to this roll out.
        return contactService.listContacts(pageable);
    }


    /**
     * List all contacts by state in the system
     *
     * @param pageable Pagination object that contains page attributes to filter on listing
     * @return The list of contact by state  available in the system
     */
    @ApiOperation(value = "list all Contacts in a state the system", notes = "Paginated list of all Contacts in a state in the system",
            response = Contact.class, responseContainer = "List")
    @RequestMapping(value = "/contacts/state/{state}", method = RequestMethod.GET)
    @ApiResponse(code = 200, message = "Paginated list of Contacts")
    public Page<Contact> listContactsInState(@PathVariable("state")  String state, Pageable pageable) {
        // cascaded delete of all jobs that belong to this roll out.
        return contactService.listContactsInState(state,pageable);
    }
    /**
     * Update a contact in address
     *
     * @param contactRequest The contact request object serialized into a JSON on the post request.
     * @return The Concat object confirming that its stored in the persistence store.
     * @throws .UniquenessException  Thrown if the contact with a similar email already exists
     * @throws .MissingDataException  Thrown if the contact with a similar email already exists
     */
    @ApiOperation(value = "Update contact", notes = "Update contact that exists on unique phone number")
    @RequestMapping(value = "/contact/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Contact.class),
            @ApiResponse(code = 400, message = "Bad request if either a contact already exists with the  same number  "
                    , response = String.class)
    })
    public Contact updateContact(@ApiParam(value = "ContactRequest payload as JSON for updating Contact")
                                 @RequestBody ContactRequest contactRequest) throws MissingDataException {
        if(contactRequest==null){

            return null;
        }
        return contactService.updateContact(Contact.getContactFromRequest(contactRequest));
    }



    /**
     * Delete a specific contact based on phone number
     *
     * @param identifier The unique phone number for a contact
     */
    @ApiOperation(value = "Delete an existing contact", notes = "Remove an exisitng contact ")
    @RequestMapping(value = "/contact", method = RequestMethod.DELETE)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad request if id not present ")
    })
    public void deleteContact(@RequestParam(value = "id") Long identifier) {

        contactService.deleteContact(identifier);
    }

}
