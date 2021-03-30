package org.example.tomasBarauskas.service.financeManager;

import org.example.tomasBarauskas.exception.finance.InsufficientFunds;
import org.example.tomasBarauskas.model.CompanyAccount;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.user.User;

import java.time.LocalDateTime;

public interface FinanceManager {

    void chargeMoneyForParking(ParkingTicket ticket, User user, CompanyAccount account);

    void checkIfEnoughMoneyInUserAccountForParkingTicket(ParkingZone zone, LocalDateTime beginParking, LocalDateTime endParking, User user) throws InsufficientFunds;
}
