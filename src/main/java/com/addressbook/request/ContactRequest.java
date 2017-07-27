package com.addressbook.request;

import java.util.Date;

/**
 * Created by sharatjagannath on 7/24/17.
 */
public class ContactRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String streetAddress;
    private String state;
    private String email;
    private long id;
    private String lastContactDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastContactDate() {
        return lastContactDate;
    }

    public void setLastContactDate(String lastContactDate) {
        this.lastContactDate = lastContactDate;
    }
}
