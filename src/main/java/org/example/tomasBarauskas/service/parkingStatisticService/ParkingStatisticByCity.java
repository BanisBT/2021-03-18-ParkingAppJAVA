package org.example.tomasBarauskas.service.parkingStatisticService;

import org.example.tomasBarauskas.exception.statistic.NoTicketInParkingGroup;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            System.out.printf("Mieste %s nebuvo apmoketu stovejimo talonu%n", parkingDetail);
        }
        return ticketsByParkingCity;
    }

    @Override
    public List<ParkingTicket> getTicketsListByGroupAndDate(LocalDate dateFrom, LocalDate dateTo, String city) {
        return allTicketsFromDb.stream()
                .filter(ticket -> ticket.getBeginParking().toLocalDate().isAfter(dateFrom.minusDays(1)))
                .filter(ticket -> ticket.getBeginParking().toLocalDate().isBefore(dateTo.plusDays(1)))
                .filter(ticket -> ticket.getParkingCity().equals(city))
                .collect(Collectors.toList());
    }
}
