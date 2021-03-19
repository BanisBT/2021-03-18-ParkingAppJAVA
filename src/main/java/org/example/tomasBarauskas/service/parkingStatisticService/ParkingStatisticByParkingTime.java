package org.example.tomasBarauskas.service.parkingStatisticService;

import org.example.tomasBarauskas.exception.statistic.NoTicketInParkingGroup;
import org.example.tomasBarauskas.model.parking.ParkingTicket;

import java.util.ArrayList;
import java.util.List;

public class ParkingStatisticByParkingTime implements ParkingStatisticByGroupsService {

    @Override
    public List<ParkingTicket> getTicketListByGroup(String parkingDetail) {
        List<ParkingTicket> ticketsByParkingTime = new ArrayList<>();

        try {
            for (ParkingTicket ticket : allTicketsFromDb) {
                if (ticket.getParkingTime().getName().equals(parkingDetail)) {
                    ticketsByParkingTime.add(ticket);
                }
            }
        } catch (NoTicketInParkingGroup e){
            System.out.println(String.format("Pagal %s laiko trukme apmoketu stovejimu nera", parkingDetail));
        }
        return ticketsByParkingTime;
    }
}
