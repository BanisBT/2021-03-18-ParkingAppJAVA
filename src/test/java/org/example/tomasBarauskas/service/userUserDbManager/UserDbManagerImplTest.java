package org.example.tomasBarauskas.service.userUserDbManager;

import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.exception.userDataBase.RegistrationIdAlreadyExist;
import org.example.tomasBarauskas.exception.userDataBase.WrongLogInPassword;
import org.example.tomasBarauskas.model.user.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserDbManagerImplTest {

    private UserDbManager userDbManager = new UserDbManagerImpl();
    private final User BANIS = new User("Banis", "Banis", "Tomas", "Barauskas");
    private final User NOT_BANIS = new User("neBanis", "neBanis", "NeTomas", "NeBarauskas");

    @Test
    void findUserByID_shouldFindUserIfIdExist_rightUser() throws NoUserInDbByID {
        assertEquals(BANIS, userDbManager.findUserByID(BANIS.getUserId()));
    }

    @Test
    void findUserByID_shouldThrowExceptionIfIdDonNotExist_noUserByID() {
        assertThrows(NoUserInDbByID.class, () -> userDbManager.findUserByID(NOT_BANIS.getUserId()));
    }

    @Test
    void logInCheckDetails_shouldReturnUserIfIdAndPasswordRight_LogInAction() throws ClassNotFoundException, NoUserInDbByID, WrongLogInPassword, IOException {
        assertEquals(BANIS, userDbManager.logInCheckDetails(BANIS.getUserId(), BANIS.getPassword()));
    }

    @Test
    void registrationCheckIfIdExist_shouldThrowExceptionIfIdAlreadyExist_LogInAction() {
        assertThrows(RegistrationIdAlreadyExist.class, () -> userDbManager.registrationCheckIfIdExist(BANIS.getUserId()));
    }
}