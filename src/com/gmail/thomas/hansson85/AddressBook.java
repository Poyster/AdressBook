package com.gmail.thomas.hansson85;

import java.io.Serializable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressBook implements Serializable {

    Runnable autoSave;
    Register register = new Register();
    FileHandler fileHandler = new FileHandler();
    private final static Logger logger = Logger.getLogger(AddressBook.class.getName());

    public void mainMenu() {

        autoSave = new AutoSave(register);
        Thread thread = new Thread(autoSave);
        thread.start();
        logger.log(Level.INFO, "User starts the Address Book");

        String menuInput = "";
        Scanner sc = new Scanner(System.in);


        while (!menuInput.equals("quit")) {

            System.out.println("\nWelcome! Type 'help' to see available commands" + '\n');

            menuInput = sc.nextLine();
            String[] splitInput = menuInput.split(" ");

            switch (splitInput[0]) {

                case "add":
                    try {
                        register.addContact(splitInput[1], splitInput[2], splitInput[3]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        register.arrayIndex(e);
                    }
                    if (splitInput.length > 4) {
                        register.toMuchInput();
                        break;
                    }
                    break;
                case "list":
                    register.showAllContacts(register.contacts);
                    break;

                case "search":
                    register.searchForContacts(splitInput[1]);
                    break;

                case "help":
                    register.showHelpMenu();
                    break;
                case "delete":
                    register.deleteContact(splitInput[1]);
                    break;

                case "quit":
                    fileHandler.saveToFile(register.getContacts());
                    register.quitProgram();

                default:
                    register.invalidCommand(splitInput[0]);
                    break;
            }


        }

    }

}

