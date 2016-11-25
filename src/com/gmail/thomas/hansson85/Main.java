package com.gmail.thomas.hansson85;

import static com.gmail.thomas.hansson85.LogHandler.setupLogging;

public class Main {

    public static void main(String[] args) {
        setupLogging();
        AddressBook addressBook = new AddressBook();
        addressBook.mainMenu();
    }



}

