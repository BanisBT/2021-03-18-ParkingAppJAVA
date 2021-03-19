package org.example.tomasBarauskas.service.parkingTicketDbManager;

import org.example.tomasBarauskas.model.parking.ParkingTicket;

import java.util.ArrayList;
import java.util.List;

public class ParkingTicketDbManagerImpl implements ParkingTicketDbManager {
    private static List<ParkingTicket> parkingTickets = new ArrayList<>();

    public ParkingTicketDbManagerImpl() {
    }

    @Override
    public void addTicketToDb(ParkingTicket parkingTicket) {
        parkingTickets.add(parkingTicket);
    }

    @Override
    public List<ParkingTicket> getParkingTicketsDb() {
        return parkingTickets;
    }
}
