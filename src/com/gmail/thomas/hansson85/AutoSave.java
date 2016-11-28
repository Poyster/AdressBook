package com.gmail.thomas.hansson85;


import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoSave implements Runnable{
    Register register = new Register();
    private final static Logger logger = Logger.getLogger(AutoSave.class.getName());
    FileHandler fileHandler = new FileHandler();

    public AutoSave(Register register) {
       this.register = register;
    }

    @Override
    public void run() {
    while (true){
    try{
        Thread.sleep(5000);
    }catch (InterruptedException e){
        logger.log(Level.SEVERE, "InterruptedException", e);
    }
        fileHandler.saveToFile(register);

}

    }
}
