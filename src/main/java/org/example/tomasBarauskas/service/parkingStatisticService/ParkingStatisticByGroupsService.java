package org.example.tomasBarauskas.service.parkingStatisticService;

import org.example.tomasBarauskas.model.parking.ParkingTicket;
import org.example.tomasBarauskas.service.parkingTicketDbManager.ParkingTicketDbManagerImpl;

import java.util.List;

public interface ParkingStatisticByGroupsService {
    List<ParkingTicket> allTicketsFromDb = new ParkingTicketDbManagerImpl().getParkingTicketsDb();

    List<ParkingTicket> getTicketListByGroup(String parkingDetail);
}
