package com.addressbook.util;

import com.addressbook.model.Contact;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

/**
 * Created by sharatjagannath on 7/26/17.
 */
public class Helpers {
    public static Contact churnContact() {
        Contact contact = new Contact();



        contact.setLastContactDate(new Date());
        long lowerLimit = 123456712L;
        long upperLimit = 234567892L;
        Random r = new Random();
        long number = lowerLimit+((long)(r.nextDouble()*(upperLimit-lowerLimit)));
        contact.setFirstName("first"+number);
        contact.setLastName("last");
        contact.setState("CA");
        contact.setEmail("test@gmail.com");
       contact.setPhoneNumber(""+number);
        return contact;
    }
}
