package org.example.tomasBarauskas.service.parkingTicketDbManager;

import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.user.User;

import java.util.List;

public interface ParkingTicketDbManager {

    void addTicketToDb(ParkingTicket parkingTicket);

    List<ParkingTicket> getParkingTicketsDb();

    List<ParkingTicket> getAllUsersParkingTickets(User user);

    void rewriteParkingTicketDetailsToFile(ParkingTicket ticketWithNewDetails);
}