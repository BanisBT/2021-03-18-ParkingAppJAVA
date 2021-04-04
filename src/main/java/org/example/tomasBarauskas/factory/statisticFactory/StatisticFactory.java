package org.example.tomasBarauskas.factory.statisticFactory;

import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.service.parkingTicketDbManager.ParkingTicketDbManager;
import org.example.tomasBarauskas.service.parkingTicketDbManager.ParkingTicketDbManagerImpl;
import org.example.tomasBarauskas.service.parkingZoneDbManager.ParkingZoneDb;
import org.example.tomasBarauskas.service.parkingZoneDbManager.ParkingZoneDbImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StatisticFactory {
    private final ParkingTicketDbManager ticketDbManager = new ParkingTicketDbManagerImpl();
    private final ParkingZoneDb zoneDb = new ParkingZoneDbImpl();

    public List<ParkingTicket> getParkingTicketsSortByDate(LocalDate fromDate, LocalDate toDate){
        return ticketDbManager.getParkingTicketsDb().stream()
                .filter(ticket -> ticket.beganParkingTicketDateIsAfterDate(fromDate))
                .filter(ticket -> ticket.beganParkingTicketDateIsBefore(toDate))
                .collect(Collectors.toList());
    }

    public List<ParkingTicket> getParkingTicketsSortByDateAndCity(LocalDate fromDate, LocalDate toDate, String parkingCity){
        List<ParkingTicket> ticketListSortByDate = getParkingTicketsSortByDate(fromDate, toDate);
        return ticketListSortByDate.stream()
                .filter(ticket -> ticket.getParkingCity().equals(parkingCity))
                .collect(Collectors.toList());
    }

    public List<ParkingTicket> getParkingTicketsSortByDateAndZone(LocalDate fromDate, LocalDate toDate, String parkingZone){
        List<ParkingTicket> ticketListSortByDate = getParkingTicketsSortByDate(fromDate, toDate);
        return ticketListSortByDate.stream()
                .filter(ticket -> ticket.getParkingZone().getName().equals(parkingZone))
                .collect(Collectors.toList());
    }

    private List<ParkingTicket> ticketListByParkingZone(ParkingZone parkingZone){
        return ticketDbManager.getParkingTicketsDb().stream()
                .filter(ticket -> ticket.getParkingZone().equals(parkingZone))
                .collect(Collectors.toList());
    }

    private BigDecimal getTotalSumOfListOfParkingTicketAmounts(List<ParkingTicket> parkingTicketList){
        BigDecimal sum = new BigDecimal( 0);
        for (ParkingTicket ticket : parkingTicketList){
            sum.add(ticket.getTicketAmount());
        }
        return sum;
    }
}