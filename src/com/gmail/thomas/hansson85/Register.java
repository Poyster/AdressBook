package com.gmail.thomas.hansson85;


import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Register implements Serializable {

    List<Contact> contacts = new ArrayList<>();
    private final static Logger logger = Logger.getLogger(Register.class.getName());
    //String uniqueID;
    Contact contact;

    public void addContact(String firstName, String surName, String emailAddress) {
        String uniqueID = UUID.randomUUID().toString();
        contact = new Contact(firstName, surName, emailAddress, uniqueID);
        contacts.add(contact);
        System.out.println("New contact successfully added.");
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void deleteContact(String uniqueID) {
      try {
          for (Contact contact : contacts) {
              if (contact.getUniqueID().equals(uniqueID)) {
                  contacts.remove(contact);
                  System.out.println("Contact deleted!");
              }
          }}catch(ConcurrentModificationException e){
              logger.log(Level.SEVERE, "", e);
          }
      }

    public void showHelpMenu(){
        System.out.printf("add\t\t - Adds a new contact to the Address Book\n" +
                "list\t - Shows all contacts in the Address Book\n" +
                "search\t - Search in the Address Book by either first name or surname\n" +
                "delete\t - Delete a contact using the contacts unique ID\n" +
                "quit\t - Exit the Address Book\n");

    }

    public void showAllContacts(List contacts) {
        List<Contact> sortedTempList = new ArrayList();
        sortedTempList.addAll(contacts);

        Collections.sort(sortedTempList);
        for (Contact contact:sortedTempList) {
            System.out.println(contact);
        }
        if (contacts.isEmpty()){
            System.out.println("There are currently no contacts in the address book.");
        }
    }

    public void searchForContacts(String searchFor) {
        searchFor = searchFor.toLowerCase();

        List<Contact> sortedTempList = new ArrayList<>();
        sortedTempList.addAll(contacts);

        for (int i = 0; i < sortedTempList.size(); i++) {
            if (getContacts().get(i).getFirstName().toLowerCase().startsWith(searchFor) || getContacts().get(i).getSurName().toLowerCase().startsWith(searchFor)) {
                Collections.sort(sortedTempList);
                System.out.println(getContacts().get(i).toString());
            } else {
                System.out.println("No contact found.");
            }
        }
    }


}
