package org.example.tomasBarauskas.service.parkingStatisticService;

import org.example.tomasBarauskas.exception.statistic.NoTicketInParkingGroup;
import org.example.tomasBarauskas.model.parking.ParkingTicket;

import java.util.ArrayList;
import java.util.List;

public class ParkingStatisticByCity implements ParkingStatisticByGroupsService{
    @Override
    public List<ParkingTicket> getTicketListByGroup(String parkingDetail) {
        List<ParkingTicket> ticketsByParkingCity = new ArrayList<>();

        try {
            for (ParkingTicket ticket : allTicketsFromDb){
                if (ticket.getParkingCity().equals(parkingDetail)){
                    ticketsByParkingCity.add(ticket);
                }
            }
        } catch (NoTicketInParkingGroup e){
            System.out.println(String.format("Mieste %s nebuvo apmoketu stovejimo talonu", parkingDetail));
        }
        return ticketsByParkingCity;
    }
}
