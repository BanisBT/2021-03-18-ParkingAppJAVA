package org.example.tomasBarauskas.service.userUserDbManager;

import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.exception.userDataBase.RegistrationIdAlreadyExist;
import org.example.tomasBarauskas.exception.userDataBase.WrongLogInPassword;
import org.example.tomasBarauskas.model.user.User;

import java.io.IOException;
import java.util.List;

public interface UserDbManager {
    void addUserToDb(User user) throws IOException, ClassNotFoundException;

    void registrationCheckIfIdExist(String checkID) throws RegistrationIdAlreadyExist, IOException, ClassNotFoundException;

    List<User> getAllUsers() throws IOException, ClassNotFoundException;

    User logInCheckDetails(String logInID, String logInPassword) throws NoUserInDbByID, WrongLogInPassword, IOException, ClassNotFoundException;

    User findUserByID(String id) throws NoUserInDbByID;

    void rewriteUserDetailsToFile(User userWithNewDetails);
}