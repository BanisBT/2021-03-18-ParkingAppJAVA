package org.example.tomasBarauskas.factory.parkingTicketFactory;

import org.example.tomasBarauskas.exception.finance.InsufficientFunds;
import org.example.tomasBarauskas.exception.parkingTicket.TicketError;
import org.example.tomasBarauskas.exception.parkingTicket.UserDontHaveOpenParkingTicket;
import org.example.tomasBarauskas.exception.parkingTicket.UserHaveOpenParkingTicket;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingCity;
import org.example.tomasBarauskas.model.parking.ParkingRecordStatus;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.service.financeManager.FinanceManager;
import org.example.tomasBarauskas.service.financeManager.FinanceManagerImpl;
import org.example.tomasBarauskas.service.parkingTicketDbManager.ParkingTicketDbManager;
import org.example.tomasBarauskas.service.parkingTicketDbManager.ParkingTicketDbManagerImpl;

import java.time.LocalDateTime;

public class ParkingTicketFactory {
    private ParkingTicketDbManager ticketDbManager = new ParkingTicketDbManagerImpl();
    private FinanceManager financeManager = new FinanceManagerImpl();

    public void parkingMachineTryToOrderParkingTicket(User user, ParkingCity parkingCity, ParkingZone parkingZone, LocalDateTime parkingTime) throws InsufficientFunds {
        financeManager.checkIfEnoughMoneyInUserAccountForParkingTicket(parkingZone, LocalDateTime.now(), parkingTime, user);

        ParkingTicket tempTicket = new ParkingTicket(parkingCity.getName(), parkingZone, user);
        tempTicket.setEndParking(parkingTime);
        tempTicket.setRecordStatus(ParkingRecordStatus.PAID);
        tempTicket.setTicketAmount(financeManager.getTicketAmount(parkingZone, LocalDateTime.now(), parkingTime));

        financeManager.chargeMoneyForParkingMachine(tempTicket, user);
    }

    public void addOpenTicketToDb(User carOwner, ParkingCity carParkedCity, ParkingZone carParkedZone) {
        ParkingTicket openTicket = new ParkingTicket(carParkedCity.getName(), carParkedZone, carOwner);
        ticketDbManager.addTicketToDb(openTicket);
    }

    public void checkBeganUserOpenTicket(User user) throws UserHaveOpenParkingTicket {
        for (ParkingTicket ticket : ticketDbManager.getAllUsersParkingTickets(user)) {
            if (checkParkingStatus(ticket.getRecordStatus(), ParkingRecordStatus.OPEN)) {
                throw new UserHaveOpenParkingTicket();
            }
        }
    }

    public void checkEndUserOpenTicket(User user) throws UserDontHaveOpenParkingTicket {
        for (ParkingTicket ticket : ticketDbManager.getAllUsersParkingTickets(user)) {
            if (checkParkingStatus(ticket.getRecordStatus(), ParkingRecordStatus.OPEN)) {
                ticket.setRecordStatus(ParkingRecordStatus.UNPAID);
                ticket.setEndParking(LocalDateTime.now());
                ticketDbManager.rewriteParkingTicketDetailsToFile(ticket);
            } else {
                throw new UserDontHaveOpenParkingTicket();
            }
        }
    }

    public void stopParkingTimeAndTryToPayForTicket(User user) throws TicketError {
        ParkingTicket ticketToPay = getUsersUnpaidTicket(user);
        ticketToPay.setEndParking(LocalDateTime.now());
        ticketDbManager.rewriteParkingTicketDetailsToFile(ticketToPay);
        try {
            financeManager.checkIfEnoughMoneyInUserAccountForParkingTicket(ticketToPay.getParkingZone(), ticketToPay.getBeginParking(), ticketToPay.getEndParking(), user);
            ticketToPay.setRecordStatus(ParkingRecordStatus.PAID);
            ticketDbManager.rewriteParkingTicketDetailsToFile(ticketToPay);
        } catch (InsufficientFunds e) {
            System.out.println("Nepakanka lesu sumoketi uz parkavimo talopna");
        }
    }

    private ParkingTicket getUsersUnpaidTicket(User user) throws TicketError {
        for (ParkingTicket ticket : ticketDbManager.getAllUsersParkingTickets(user)) {
            if (checkParkingStatus(ticket.getRecordStatus(), ParkingRecordStatus.UNPAID)) {
                return ticket;
            }
        }
        throw new TicketError();
    }

    private boolean checkParkingStatus(ParkingRecordStatus recordStatus, ParkingRecordStatus status) {
        if (recordStatus.equals(status)) {
            return true;
        }
        return false;
    }
}