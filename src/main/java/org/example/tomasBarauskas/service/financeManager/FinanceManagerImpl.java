package org.example.tomasBarauskas.service.financeManager;

import org.example.tomasBarauskas.exception.finance.InsufficientFunds;
import org.example.tomasBarauskas.model.CompanyAccount;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.user.User;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.LocalDateTime;

public class FinanceManagerImpl implements FinanceManager {
    private final MathContext MC = new MathContext(3);

    @Override
    public void chargeMoneyForParking(ParkingTicket ticket, User user, CompanyAccount account) {
        BigDecimal usersMoneyAmount = user.getCount().round(MC);
        BigDecimal moneyInCompanyAccount = account.getCompanyAccount().round(MC);
        BigDecimal ticketCost = ticket.getTicketAmount().round(MC);

        user.setCount(usersMoneyAmount.subtract(ticketCost));
        account.setCompanyAccount(moneyInCompanyAccount.add(ticketCost));
        ticket.setTicketAmount(ticketCost);
    }

    @Override
    public void checkIfEnoughMoneyInUserAccountForParkingTicket(ParkingZone zone, LocalDateTime beginTicket, LocalDateTime endTicket, User user) throws InsufficientFunds {
        BigDecimal moneyInUsersAccount = user.getCount().round(MC);
        BigDecimal ticketCost = getTicketAmount(zone, beginTicket, endTicket).round(MC);

        if (moneyInUsersAccount.subtract(ticketCost).intValue() < 0) {
            throw new InsufficientFunds();
        }
    }

    private BigDecimal countParkingTimeInHours(LocalDateTime beginParking, LocalDateTime endParking) {
        Duration duration = Duration.between(beginParking,endParking);
        double skirtumas = Math.abs(duration.toMinutes());
        double skirtumasVal = skirtumas / 60;
        BigDecimal parkingTime = BigDecimal.valueOf(skirtumasVal).round(MC);
        parkingTime = parkingTime.round(MC);

        return parkingTime;
    }

    public BigDecimal getTicketAmount(ParkingZone zone, LocalDateTime beginTicket, LocalDateTime endTicket) {
        BigDecimal parkingTime = countParkingTimeInHours(beginTicket, endTicket).round(MC);

        return zone.getCostPerHour().multiply(parkingTime);
    }
}
