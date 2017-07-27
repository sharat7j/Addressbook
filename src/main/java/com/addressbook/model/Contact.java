package com.addressbook.model;

/**
 * Created by sharatjagannath on 7/24/17.
 */
import com.addressbook.controller.AddressBookController;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.addressbook.request.ContactRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class Contact {
    final static Logger logger = LoggerFactory.getLogger(AddressBookController.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column

    private String phoneNumber;
    @Column
    private String streetAddress;
    @Column
    private String state;
    @Column
    private String email;
    @Column
    private Date lastContactDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastContactDate() {

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String date = df.format(lastContactDate);
        return date;
    }

    public void setLastContactDate(Date lastContactDate) {
        this.lastContactDate = lastContactDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public static Contact getContactFromRequest(ContactRequest request) {

        if(request == null) {

            return null;
        }

        Contact contact = new Contact();
        contact.setId(request.getId());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhoneNumber(request.getPhoneNumber());
        contact.setState(request.getState());
        contact.setStreetAddress(request.getStreetAddress());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        try {
            if(request.getLastContactDate()!=null) {
                contact.setLastContactDate(df.parse(request.getLastContactDate()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return contact;
    }
}
