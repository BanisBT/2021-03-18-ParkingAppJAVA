package org.example.tomasBarauskas.service.userDbManager;

import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.exception.userDataBase.RegistrationIdAlreadyExist;
import org.example.tomasBarauskas.exception.userDataBase.WrongLogInPassword;
import org.example.tomasBarauskas.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserDbManagerImpl implements UserDbManager{
    private List<User> usersDb;

    public UserDbManagerImpl() {
        usersDb = new ArrayList<>();
    }

    @Override
    public void addUserToDb(User user) {
        usersDb.add(user);
    }

    @Override
    public void registrationCheckIfIdExist(String checkID) throws RegistrationIdAlreadyExist {
        for (User user : usersDb) {
            if (user.getUserId().equals(checkID)) {
                throw new RegistrationIdAlreadyExist();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        return usersDb;
    }

    @Override
    public User logInCheckDetails(String logInID, String logInPassword) throws NoUserInDbByID, WrongLogInPassword {
        User userToCheckPassword = findUserByID(logInID);
        if (userToCheckPassword.getPassword().equals(logInPassword)){
            return userToCheckPassword;
        }
        throw new WrongLogInPassword();
    }

    private User findUserByID(String idForSearching) throws NoUserInDbByID {
        for (User user : usersDb) {
            if (user.getUserId().equals(idForSearching)) {
                return user;
            }
        }
        throw new NoUserInDbByID();
    }
}
