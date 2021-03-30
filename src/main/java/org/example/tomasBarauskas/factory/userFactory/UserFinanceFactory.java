package org.example.tomasBarauskas.factory.userFactory;

import org.example.tomasBarauskas.exception.finance.RestoreBalance;
import org.example.tomasBarauskas.exception.finance.TransferZeroLess;
import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.service.userUserDbManager.UserDbManager;
import org.example.tomasBarauskas.service.userUserDbManager.UserDbManagerImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class UserFinanceFactory {
    private UserDbManager userDbManager = new UserDbManagerImpl();

    public void userPutMoneyToAccount(User user, float amount) throws TransferZeroLess {
        if (amount < 0) {
            throw new TransferZeroLess();
        } else {
            try {
                userDbManager.putMoneyToUserAccount(user, amount);
            } catch (IOException | ClassNotFoundException | NoUserInDbByID e) {
                System.out.println("Klaida");
            }
        }
    }

    public BigDecimal getUserBalance(User user) throws IOException, ClassNotFoundException {
        BigDecimal userBalance = new BigDecimal(BigInteger.ZERO);
        for (User userFromDb : userDbManager.getAllUsers()) {
            if (userFromDb.equals(user)) {
                userBalance = userFromDb.getCount();
            }
        }
        return userBalance;
    }

    public void checkIfUserAmountIsNotMinus(User user) throws IOException, ClassNotFoundException, RestoreBalance {
        if (BigDecimal.ZERO.compareTo(getUserBalance(user)) > 0) {
            throw new RestoreBalance();
        }
    }
}
