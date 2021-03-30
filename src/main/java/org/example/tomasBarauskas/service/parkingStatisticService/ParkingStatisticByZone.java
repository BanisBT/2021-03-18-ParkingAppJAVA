package org.example.tomasBarauskas.service.parkingStatisticService;

import org.example.tomasBarauskas.exception.statistic.NoTicketInParkingGroup;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;

import java.time.LocalDate;
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
            System.out.printf("%s dar nera apmoketu stovejimo talonu%n", parkingDetail);
        }
        return ticketsByParkingZone;
    }

    @Override
    public List<ParkingTicket> getTicketsListByGroupAndDate(LocalDate dateFrom, LocalDate dateTo, String city) {
        return null;
    }
}
