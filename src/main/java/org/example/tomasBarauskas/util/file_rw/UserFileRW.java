package org.example.tomasBarauskas.util.file_rw;

import org.example.tomasBarauskas.model.user.User;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class UserFileRW implements FileRW {

    @Override
    public List getDetailsFromFile1(String path) throws IOException {
        List<User> usersFromFile = new ArrayList<>();
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = null;

        try {
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof User) {
                    usersFromFile.add((User) obj);
                }
            }
        } catch (EOFException | ClassNotFoundException e) {
            // failo pabaiga
        }
        return usersFromFile;
    }
}