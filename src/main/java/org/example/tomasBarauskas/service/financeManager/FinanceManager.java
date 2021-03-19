package org.example.tomasBarauskas.service.financeManager;

import org.example.tomasBarauskas.exception.finance.InsufficientFunds;
import org.example.tomasBarauskas.model.CompanyAccount;
import org.example.tomasBarauskas.model.parking.ParkingTicket;
import org.example.tomasBarauskas.model.parking.ParkingTime;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.user.RegularUser;

public interface FinanceManager {
    void chargeMoneyForParking(ParkingTicket ticket, RegularUser user, CompanyAccount account);
    void checkIfEnoughMoneyInUserAccountForParkingTicket(ParkingZone zone, ParkingTime time, RegularUser user) throws InsufficientFunds;
}
