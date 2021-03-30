package org.example.tomasBarauskas.factory.userFactory;

import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.exception.userDataBase.RegistrationIdAlreadyExist;
import org.example.tomasBarauskas.exception.userDataBase.WrongLogInPassword;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.model.user.UserRole;
import org.example.tomasBarauskas.service.userUserDbManager.UserDbManager;
import org.example.tomasBarauskas.service.userUserDbManager.UserDbManagerImpl;

import java.io.IOException;

public class UserLogInFactory {
    private UserDbManager userDbManager = new UserDbManagerImpl();

    public void registrationCheckIfIdExist(String id) throws RegistrationIdAlreadyExist, IOException, ClassNotFoundException {
        userDbManager.registrationCheckIfIdExist(id);
    }

    public void regularUserRegistration(String id, String password, String name, String surname, String carNumber) throws IOException, ClassNotFoundException {
        User userToRegistration = new User(id, password, name, surname);
        userToRegistration.setCarNumber(carNumber);
        userDbManager.addUserToDb(userToRegistration);
    }

    public void managerUserRegistration(String id, String password, String name, String surname) throws IOException, ClassNotFoundException {
        User managerToRegistration = new User(id, password, name, surname);
        managerToRegistration.setRole(UserRole.MANAGER_ROLE);
        userDbManager.addUserToDb(managerToRegistration);
    }

    public User checkUserLogInDetails(String id, String password) throws NoUserInDbByID {
        User logInUser = null;
        try {
            logInUser = userDbManager.logInCheckDetails(id, password);
            return logInUser;
        } catch (NoUserInDbByID | WrongLogInPassword | IOException | ClassNotFoundException noUserInDbByID) {
            noUserInDbByID.printStackTrace();
        }
        throw new NoUserInDbByID();
    }

    public boolean switchMenuByUserRole(User user) {
        return user.getRole().equals(UserRole.REGULAR_ROLE);
    }
}

