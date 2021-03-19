package org.example.tomasBarauskas.service.parkingStatisticService;

import org.example.tomasBarauskas.exception.statistic.NoTicketInParkingGroup;
import org.example.tomasBarauskas.model.parking.ParkingTicket;

import java.util.ArrayList;
import java.util.List;

public class ParkingStatisticByZone implements ParkingStatisticByGroupsService {
    @Override
    public List<ParkingTicket> getTicketListByGroup(String parkingDetail) {
        List<ParkingTicket> ticketsByParkingZone = new ArrayList<>();

        try {
            for (ParkingTicket ticket : allTicketsFromDb) {
                if (ticket.getParkingZone().getName().equals(parkingDetail)) {
                    ticketsByParkingZone.add(ticket);
                }
            }
        } catch (NoTicketInParkingGroup e) {
            System.out.println(String.format("%s dar nera apmoketu stovejimo talonu", parkingDetail));
        }
        return ticketsByParkingZone;
    }
}
