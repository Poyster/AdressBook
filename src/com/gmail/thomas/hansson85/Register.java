package com.gmail.thomas.hansson85;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Register implements Serializable {

    private FileHandler fileHandler = new FileHandler();
    private Printer printer = new Printer();
    ArrayList<Contact> contacts;
    private final static Logger logger = Logger.getLogger(Register.class.getName());
    private Contact contact;

    public Register() {
        contacts = fileHandler.loadOnStart();
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
    }

    public void addContact(String firstName, String surName, String emailAddress) {

        String uniqueID = UUID.randomUUID().toString();
        contact = new Contact(firstName, surName, emailAddress, uniqueID);
        contacts.add(contact);
        System.out.println("New contact successfully added.");
        logger.log(Level.FINE, "User added a new contact");
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void deleteContact(String uniqueID) {
        Contact contactToRemove = null;
        for (Contact contact : contacts) {
            if (uniqueID.equals(contact.getUniqueID())) {
                contactToRemove = contact;
            }
        }
        if (contactToRemove != null) {
            contacts.remove(contactToRemove);
            System.out.println("Contact with ID: " + uniqueID + " deleted!");
            logger.log(Level.FINE, "User deleted a contact with ID: " + uniqueID);
        }else {
            if(contactToRemove == null){
                System.out.println("No contact with that ID found, please try again.");
                logger.log(Level.FINE, "User tried to delete contact, but no contact found with input ID");
            }
        }


    }

    public void showHelpMenu() {
        System.out.printf("add\t\t - Adds a new contact to the Address Book. Format: <firstname> <lastname> <email-address>\n" +
                "list\t - Shows all contacts in the Address Book\n" +
                "search\t - Search in the Address Book by either first name or surname\n" +
                "delete\t - Delete a contact using the contacts unique ID\n" +
                "quit\t - Exit the Address Book\n");

        logger.log(Level.FINE, "User looked at the help list with commands");
    }

    public void showAllContacts(ArrayList contacts) {

        printer.printAnSortedList(contacts);

    }

    public void searchForContacts(String searchFor) {
        searchFor = searchFor.toLowerCase();
        String result = null;

        ArrayList<Contact> sortedTempList = new ArrayList<>();
        sortedTempList.addAll(contacts);

        for (Contact contact : sortedTempList) {

            if (contact.getFirstName().toLowerCase().startsWith(searchFor) || contact.getSurName().toLowerCase().startsWith(searchFor)) {
                ArrayList<Contact> sortedSearchList = new ArrayList<>();
                sortedSearchList.add(contact);
                result = contact.getFirstName();
                printer.printAnSearchedList(sortedSearchList);
                logger.log(Level.FINE, "User searched for contacts with the search phrase: " + searchFor);
            }
        }
        if (result == null) {
            System.out.println("No contact found.");
            logger.log(Level.FINE, "User searched for contacts with no result found");
        }

    }

    public void quitProgram() {
        logger.log(Level.INFO, "User exits Address Book");
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public void arrayIndex(ArrayIndexOutOfBoundsException e) {
        logger.log(Level.SEVERE, "Not enough input data by user", e);
        System.out.println("Not enough input, please try again.");
    }

    public void toMuchInputRemovedAndAddedContact() {
        System.out.println("To much input added, removed unnecessary input");
    }

    public void invalidCommand(String command) {
        System.out.println(command + " is not a valid command, please try again.");
    }

}
