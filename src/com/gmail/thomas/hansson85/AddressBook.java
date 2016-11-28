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
                        toRegister.arrayIndex(e);
                    }
                    if (splitInput.length > 4) {
                        toRegister.toMuchInput();
                        break;
                    }
                    break;
                case "list":
                    toRegister.showAllContacts(toRegister.contacts);
                    break;

                case "search":
                    toRegister.searchForContacts(splitInput[1]);
                    break;

                case "help":
                    toRegister.showHelpMenu();
                    break;
                case "delete":
                    toRegister.deleteContact(splitInput[1]);
                    break;

                case "quit":
                    storageBook.saveToFile(toRegister);
                    toRegister.quitProgram();

                default:
                    toRegister.invalidCommand(splitInput[0]);
                    break;
            }


        }

    }

}

