package com.gmail.thomas.hansson85;

import static com.gmail.thomas.hansson85.LogHandler.setupLogging;

public class Program {

    private UserInputHandler userInputHandler = new UserInputHandler();

    public void run() {
        setupLogging();
        userInputHandler.mainMenu();
    }
}
