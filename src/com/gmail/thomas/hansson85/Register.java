package com.gmail.thomas.hansson85;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Register implements Serializable {

    ArrayList<Contact> contacts = new ArrayList<>();
    private final static Logger logger = Logger.getLogger(Register.class.getName());
    Contact contact;

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
          if(contactToRemove != null){
              contacts.remove(contactToRemove);
              System.out.println("Contact with ID: " + uniqueID + " deleted!");
              logger.log(Level.FINE, "User deleted a contact with ID: " + uniqueID);
          }

      }

    public void showHelpMenu(){
        System.out.printf("add\t\t - Adds a new contact to the Address Book. Format: <firstname> <lastname> <email-address>\n" +
                "list\t - Shows all contacts in the Address Book\n" +
                "search\t - Search in the Address Book by either first name or surname\n" +
                "delete\t - Delete a contact using the contacts unique ID\n" +
                "quit\t - Exit the Address Book\n");

        logger.log(Level.FINE, "User looked at the help list with commands");
    }

    public void showAllContacts(ArrayList contacts) {
        ArrayList<Contact> sortedTempList = new ArrayList();
        sortedTempList.addAll(contacts);

        Collections.sort(sortedTempList);
        for (Contact contact:sortedTempList) {
            System.out.println(contact);
        }
        logger.log(Level.FINE, "User looked at a list of all contacts");
        if (contacts.isEmpty()){
            System.out.println("There are currently no contacts in the address book.");
            logger.log(Level.FINE, "User looked at an empty contact list");
        }
    }

    public void searchForContacts(String searchFor) {
        searchFor = searchFor.toLowerCase();
        String result = null;

        ArrayList<Contact> sortedTempList = new ArrayList<>();
        sortedTempList.addAll(contacts);

        for (int i = 0; i < sortedTempList.size(); i++) {
            if (getContacts().get(i).getFirstName().toLowerCase().startsWith(searchFor) || getContacts().get(i).getSurName().toLowerCase().startsWith(searchFor)) {
                Collections.sort(sortedTempList);
                result = getContacts().get(i).toString();
                System.out.println(getContacts().get(i).toString());
                logger.log(Level.FINE, "User searched for contacts with the search phrase: " + searchFor);
            }

        }
        if(result == null){
            System.out.println("No contact found.");
            logger.log(Level.FINE, "User searched for contacts with no result found");
        }

    }
    public void quitProgram(){
        logger.log(Level.INFO, "User exits Address Book");
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public void arrayIndex(ArrayIndexOutOfBoundsException e){
        logger.log(Level.SEVERE, "Not enough input data by user", e);
        System.out.println("Not enough input, try again");
    }

    public void toMuchInput(){
        System.out.println("To much input added, removed unnecessary input");
    }
    public void invalidCommand(String command){
        System.out.println(command + " is not a valid command, please try again.");
    }


}
