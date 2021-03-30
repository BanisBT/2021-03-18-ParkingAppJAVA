package org.example.tomasBarauskas.factory.parkingTicketFactory;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.example.tomasBarauskas.exception.parkingTicket.UserDontHaveOpenParkingTicket;
import org.example.tomasBarauskas.exception.parkingTicket.UserHaveOpenParkingTicket;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingCity;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingRecordStatus;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.service.parkingTicketDbManager.ParkingTicketDbManager;
import org.example.tomasBarauskas.service.parkingTicketDbManager.ParkingTicketDbManagerImpl;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingTicketFactory {
    private ParkingTicketDbManager ticketDbManager = new ParkingTicketDbManagerImpl();

    public void addOpenTicketToDb(User carOwner, ParkingCity carParkedCity, ParkingZone carParkedZone) {
        ParkingTicket openTicket = new ParkingTicket(carParkedCity.getName(), carParkedZone, carOwner);
        ticketDbManager.addTicketToDb(openTicket);
    }

    public void checkBeganUserOpenTicket(User user) throws UserHaveOpenParkingTicket {
        List<ParkingTicket> allUserTicketsFromDb = ticketDbManager.getAllUsersParkingTickets(user);
        for (ParkingTicket ticket : allUserTicketsFromDb){
            if (ticket.getRecordStatus().equals(ParkingRecordStatus.OPEN)){
                throw new UserHaveOpenParkingTicket();
            }
        }
    }

    public void checkEndUserOpenTicket(User user) throws UserDontHaveOpenParkingTicket {
        List<ParkingTicket> allUserTicketsFromDb = ticketDbManager.getAllUsersParkingTickets(user);
        for (ParkingTicket ticket : allUserTicketsFromDb){
            if (ticket.getRecordStatus().equals(ParkingRecordStatus.OPEN)){
                ticket.setRecordStatus(ParkingRecordStatus.UNPAID);
                ticket.setEndParking(LocalDateTime.now());
                ticketDbManager.addTicketToDb(ticket);
            } else {
                throw new UserDontHaveOpenParkingTicket();
            }
        }
    }
}
