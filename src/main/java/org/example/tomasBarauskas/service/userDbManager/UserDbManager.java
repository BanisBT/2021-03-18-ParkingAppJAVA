package org.example.tomasBarauskas.service.userDbManager;

import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.exception.userDataBase.RegistrationIdAlreadyExist;
import org.example.tomasBarauskas.exception.userDataBase.WrongLogInPassword;
import org.example.tomasBarauskas.model.user.User;

import java.util.List;

public interface UserDbManager {
    void addUserToDb(User user);

    void registrationCheckIfIdExist(String checkID) throws RegistrationIdAlreadyExist;

    List<User> getAllUsers();

    User logInCheckDetails(String logInID, String logInPassword) throws NoUserInDbByID, WrongLogInPassword;
}
