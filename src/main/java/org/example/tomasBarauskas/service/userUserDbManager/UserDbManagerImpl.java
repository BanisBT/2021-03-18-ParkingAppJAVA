package org.example.tomasBarauskas.service.userUserDbManager;

import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.exception.userDataBase.RegistrationIdAlreadyExist;
import org.example.tomasBarauskas.exception.userDataBase.WrongLogInPassword;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.util.json.FileJsonRW;

import java.util.List;

public class UserDbManagerImpl implements UserDbManager {
    private final String PATH_USER_LIST = "target/users.json";

    private FileJsonRW jsonRW = new FileJsonRW();
    private List<User> usersDb = jsonRW.jsonReadUsersFromFile();

    public UserDbManagerImpl() {
    }

    @Override
    public void addUserToDb(User user) {
        usersDb = getAllUsers();
        usersDb.add(user);
        writeUserDbToFile(usersDb);
    }

    @Override
    public void registrationCheckIfIdExist(String checkID) throws RegistrationIdAlreadyExist {
        usersDb = getAllUsers();
        for (User user : usersDb) {
            if (user.getUserId().equals(checkID)) {
                throw new RegistrationIdAlreadyExist();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        return jsonRW.jsonReadUsersFromFile();
    }

    @Override
    public User logInCheckDetails(String logInID, String logInPassword) throws NoUserInDbByID, WrongLogInPassword {
        User userToCheckPassword = findUserByID(logInID);
        if (userToCheckPassword.getPassword().equals(logInPassword)) {
            return userToCheckPassword;
        }
        throw new WrongLogInPassword();
    }

    @Override
    public User findUserByID(String idForSearching) throws NoUserInDbByID {
        usersDb = getAllUsers();
        for (User user : usersDb) {
            if (user.getUserId().equals(idForSearching)) {
                return user;
            }
        }
        throw new NoUserInDbByID();
    }

    @Override
    public void rewriteUserDetailsToFile(User userWithNewDetails) {
        usersDb = getAllUsers();
        for (User user : usersDb){
            if (user.equals(userWithNewDetails)){
                user.setCarNumber(userWithNewDetails.getCarNumber());
                user.setCount(userWithNewDetails.getCount());
                writeUserDbToFile(usersDb);
            }
        }
    }

    @Override
    public User findUserByCarNumber(String carNumber) {
        usersDb = getAllUsers();
        for (User user : usersDb){
            if (carNumber.equals(user.getCarNumber())){
                return user;
            }
        }
        return null;
    }

    private void writeUserDbToFile(List<User> userList) {
        jsonRW.jsonWriteObjectListToFile(userList, PATH_USER_LIST);
    }
}