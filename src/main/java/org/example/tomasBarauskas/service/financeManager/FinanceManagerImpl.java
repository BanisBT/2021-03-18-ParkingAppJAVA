package org.example.tomasBarauskas.service.financeManager;

import org.example.tomasBarauskas.exception.finance.InsufficientFunds;
import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.model.CompanyAccount;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.service.parkingTicketDbManager.ParkingTicketDbManager;
import org.example.tomasBarauskas.service.parkingTicketDbManager.ParkingTicketDbManagerImpl;
import org.example.tomasBarauskas.service.userUserDbManager.UserDbManager;
import org.example.tomasBarauskas.service.userUserDbManager.UserDbManagerImpl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.LocalDateTime;

public class FinanceManagerImpl implements FinanceManager {
    private final MathContext MC = new MathContext(3);
    private UserDbManager userDb = new UserDbManagerImpl();
    private CompanyAccount companyAccount = new CompanyAccount();
    private ParkingTicketDbManager ticketDb = new ParkingTicketDbManagerImpl();

    @Override
    public void chargeMoneyForParkingMachine(ParkingTicket ticket, User user) {
        BigDecimal usersMoneyAmount = user.getCount().round(MC);
        BigDecimal moneyInCompanyAccount = companyAccount.getCompanyAccount().round(MC);
        BigDecimal ticketCost = ticket.getTicketAmount().round(MC);

        user.setCount(usersMoneyAmount.subtract(ticketCost));
        companyAccount.setCompanyAccount(moneyInCompanyAccount.add(ticketCost));
        ticket.setTicketAmount(ticketCost);

        userDb.rewriteUserDetailsToFile(user);
    }

    @Override
    public void checkIfEnoughMoneyInUserAccountForParkingTicket(ParkingZone zone, LocalDateTime beginTicket, LocalDateTime endTicket, User user) throws InsufficientFunds {
        BigDecimal moneyInUsersAccount = user.getCount().round(MC);
        BigDecimal ticketCost = getTicketAmount(zone, beginTicket, endTicket).round(MC);

        if (moneyInUsersAccount.subtract(ticketCost).intValue() < 0) {
            throw new InsufficientFunds();
        }
    }

    @Override
    public BigDecimal getTicketAmount(ParkingZone zone, LocalDateTime beginTicket, LocalDateTime endTicket) {
        BigDecimal parkingTime = countParkingTimeInHours(beginTicket, endTicket).round(MC);
        return zone.getCostPerHour().multiply(parkingTime);
    }

    private BigDecimal countParkingTimeInHours(LocalDateTime beginParking, LocalDateTime endParking) {
        Duration duration = Duration.between(beginParking, endParking);
        double differenceBetweenBeganEndTime = Math.abs(duration.toMinutes());
        double differanceInHours = differenceBetweenBeganEndTime / 60;

        BigDecimal parkingTime = BigDecimal.valueOf(differanceInHours).round(MC);
        parkingTime = parkingTime.round(MC);

        return parkingTime;
    }
}