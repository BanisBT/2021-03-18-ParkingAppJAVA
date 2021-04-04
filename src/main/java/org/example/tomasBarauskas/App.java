package org.example.tomasBarauskas;

import org.example.tomasBarauskas.factory.InputMenu;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        InputMenu startProgram = new InputMenu();
        startProgram.StartProgram();
    }
}