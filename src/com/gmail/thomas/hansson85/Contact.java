package com.gmail.thomas.hansson85;

import java.io.*;

public class Contact implements Serializable, Comparable<Contact> {

    private String firstName, surName, emailAddress, uniqueID;

    public Contact(String firstName, String lastName, String emailAddress, String uniqueID) {
        this.firstName = firstName;
        this.surName = lastName;
        this.emailAddress = emailAddress;
        this.uniqueID = uniqueID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getUniqueID() { return uniqueID; }

    public String getEmailAddress() { return emailAddress; }

    @Override
    public int compareTo(Contact contact) {
        return firstName.compareTo(contact.getFirstName());
    }
}
