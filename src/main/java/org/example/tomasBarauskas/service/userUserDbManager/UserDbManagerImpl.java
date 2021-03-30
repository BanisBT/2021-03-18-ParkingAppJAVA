package org.example.tomasBarauskas.service.userUserDbManager;

import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.exception.userDataBase.RegistrationIdAlreadyExist;
import org.example.tomasBarauskas.exception.userDataBase.WrongLogInPassword;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.util.FileRW;
import org.example.tomasBarauskas.util.UserFileRW;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserDbManagerImpl implements UserDbManager {
    private final String PATH_USER_DB_FILE = "/Users/Gabi/IdeaProjects/2021-03-10/2021-03-19-ParkingAppDarbas/src/main/java/org/example/tomasBarauskas/file/UserDatabase.ser";

    private FileRW userFileRW = new UserFileRW();
    private List<User> usersDb = new ArrayList<>();

    public UserDbManagerImpl() {
        try {
            usersDb = userFileRW.getDetailsFromFile1(PATH_USER_DB_FILE);
        } catch (IOException e) {
            System.out.println("Nepavyko uzkrauti duomenu");
        }
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
        try {
            usersDb = userFileRW.getDetailsFromFile1(PATH_USER_DB_FILE);
        } catch (IOException e) {
            System.out.println("Klaida");
        }
        return usersDb;
    }

    @Override
    public User logInCheckDetails(String logInID, String logInPassword) throws NoUserInDbByID, WrongLogInPassword{
        User userToCheckPassword = findUserByID(logInID);
        if (userToCheckPassword.getPassword().equals(logInPassword)) {
            return userToCheckPassword;
        }
        throw new WrongLogInPassword();
    }

    @Override
    public void putMoneyToUserAccount(User user, float putAmount){
        usersDb = getAllUsers();
        BigDecimal putAmountBigDecimal = BigDecimal.valueOf(putAmount);
        BigDecimal amountInUserCount = user.getCount();

        for (User userTemp : usersDb) {
            if (userTemp.equals(user)) {
                userTemp.setCount(putAmountBigDecimal.add(amountInUserCount));
            }
        }
        writeUserDbToFile(usersDb);
    }

    @Override
    public void changeUserCarNumber(User user, String newCarNumber){
        usersDb = getAllUsers();

        for (User userTemp : usersDb) {
            if (userTemp.equals(user)) {
                userTemp.setCarNumber(newCarNumber);
            }
        }
        writeUserDbToFile(usersDb);
    }

    private User findUserByID(String idForSearching) throws NoUserInDbByID{
        usersDb = getAllUsers();

        for (User user : usersDb) {
            if (user.getUserId().equals(idForSearching)) {
                return user;
            }
        }
        throw new NoUserInDbByID();
    }

    private void writeUserDbToFile(List<User> userList){
        try {
            userFileRW.writeObjectDetailsToFile(PATH_USER_DB_FILE, userList);
        } catch (IOException e) {
            System.out.println("Klaida");
        }
    }
}
