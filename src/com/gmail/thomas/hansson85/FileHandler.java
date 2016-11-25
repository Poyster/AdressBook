package com.gmail.thomas.hansson85;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandler implements Serializable {
    File file = new File("AddressBookStart");
    private final static Logger logger = Logger.getLogger(FileHandler.class.getName());


    public Register loadOnStart() {
        Register a = new Register();
        if (file.exists() && !file.isDirectory()) {

            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                a = (Register) ois.readObject();
            }catch(FileNotFoundException e){
                logger.log(Level.SEVERE, "File not found", e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Class not found", e);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Something wrong occurred", e);
            }
            return a;
        } else return a;
    }

    public synchronized void saveToFile(Register addressBook) {

        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(addressBook);
            logger.log(Level.INFO, "Saving AddressBook");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Something wrong occurred", e);
        }
    }
}
