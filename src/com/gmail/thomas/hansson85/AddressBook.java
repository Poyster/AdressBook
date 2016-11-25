package com.gmail.thomas.hansson85;

import java.io.Serializable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressBook implements Serializable {

    Runnable autoSave;
    Register toRegister = new Register();
    FileHandler storageBook = new FileHandler();
    private final static Logger logger = Logger.getLogger(AddressBook.class.getName());

    public void mainMenu() {

        autoSave = new AutoSave(toRegister);
        Thread thread = new Thread(autoSave);
        thread.start();
        logger.log(Level.INFO, "User starts the Address Book");
        toRegister = storageBook.loadOnStart();

        String menuInput = "";
        Scanner sc = new Scanner(System.in);


        while (!menuInput.equals("quit")) {

            System.out.println("\nWelcome! Type 'help' to see available commands" + '\n');

            menuInput = sc.nextLine();
            String[] splitInput = menuInput.split(" ");

            switch (splitInput[0]) {

                case "add":

                    try {
                        toRegister.addContact(splitInput[1], splitInput[2], splitInput[3]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        logger.log(Level.SEVERE, "Not enough input data by user", e);
                        System.out.println("Not enough input, try again");
                    }
                    if (splitInput.length > 4) {
                        System.out.println("To much input added, removed unnecessary input");
                        break;
                    }

                    logger.log(Level.FINE, "User added a new contact");
                    break;

                case "list":
                    toRegister.showAllContacts(toRegister.contacts);
                    logger.log(Level.FINE, "User looked at a list of all contacts");
                    break;

                case "search":
                    toRegister.searchForContacts(splitInput[1]);
                    logger.log(Level.FINE, "User searched for contacts");
                    break;

                case "help":
                    toRegister.showHelpMenu();
                    logger.log(Level.FINE, "User looked at the help list with commands");
                    break;
                case "delete":
                    toRegister.deleteContact(splitInput[1]);
                    logger.log(Level.FINE, "User deleted a contact");
                    break;

                case "quit":
                    logger.log(Level.INFO, "User exits Address Book");
                    storageBook.saveToFile(toRegister);
                    System.out.println("Goodbye!");

                    System.exit(0);

                default:
                    System.out.println(splitInput[0] + " is not a valid command, please try again.");
                    break;
            }


        }

    }

}

