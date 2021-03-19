package org.example.tomasBarauskas.service.parkingTicketDbManager;

import org.example.tomasBarauskas.model.parking.ParkingTicket;

import java.util.List;

public interface ParkingTicketDbManager {
    void addTicketToDb(ParkingTicket parkingTicket);

    List<ParkingTicket> getParkingTicketsDb();
}
