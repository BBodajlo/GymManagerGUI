package com.example.gymmanagergui;

import java.io.FileNotFoundException;

/**
 * This class contains the main method, which starts the run method
 * from GymManager class that will begin taking inputs from the user to
 * manage members and the member databases.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class RunProject1 {
    public static void main(String[] args) throws FileNotFoundException {
        new GymManager().run();

    }
}

