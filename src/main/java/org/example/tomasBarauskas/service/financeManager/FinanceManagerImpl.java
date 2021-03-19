package org.example.tomasBarauskas.service.financeManager;

import org.example.tomasBarauskas.exception.finance.InsufficientFunds;
import org.example.tomasBarauskas.model.CompanyAccount;
import org.example.tomasBarauskas.model.parking.ParkingTicket;
import org.example.tomasBarauskas.model.parking.ParkingTime;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.user.RegularUser;

import java.math.BigDecimal;

public class FinanceManagerImpl implements FinanceManager {

    @Override
    public void chargeMoneyForParking(ParkingTicket ticket, RegularUser user, CompanyAccount account){
        BigDecimal usersMoneyAmount = user.getCount();
        BigDecimal moneyInCompanyAccount = account.getCompanyAccount();
        BigDecimal ticketCost =ticket.getParkingTime().getHourInDoubleValue().multiply(ticket.getParkingZone().getCostPerHour());

        user.setCount(usersMoneyAmount.subtract(ticketCost));
        account.setCompanyAccount(moneyInCompanyAccount.add(ticketCost));

    }

    @Override
    public void checkIfEnoughMoneyInUserAccountForParkingTicket(ParkingZone zone, ParkingTime time, RegularUser user) throws InsufficientFunds {
        BigDecimal moneyInUsersAccount = user.getCount();
        BigDecimal costOfTicket = zone.getCostPerHour().multiply(time.getHourInDoubleValue());

        if (moneyInUsersAccount.subtract(costOfTicket).intValue() < 0) {
            throw new InsufficientFunds();
        }
    }
}
