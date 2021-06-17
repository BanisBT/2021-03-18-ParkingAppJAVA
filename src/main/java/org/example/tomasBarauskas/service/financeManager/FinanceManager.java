package org.example.tomasBarauskas.service.financeManager;

import org.example.tomasBarauskas.exception.finance.InsufficientFunds;
import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.model.CompanyAccount;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FinanceManager {

    void chargeMoneyForParkingMachine(ParkingTicket ticket, User user);

    void checkIfEnoughMoneyInUserAccountForParkingTicket(ParkingZone zone, LocalDateTime beginParking, LocalDateTime endParking, User user) throws InsufficientFunds;

    BigDecimal getTicketAmount(ParkingZone zone, LocalDateTime beginTicket, LocalDateTime endTicket);
}