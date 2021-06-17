package org.example.tomasBarauskas.service.parkingTicketDbManager;

import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.util.json.FileJsonRW;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingTicketDbManagerImpl implements ParkingTicketDbManager {
    private final String PATH_TICKET_LIST = "target/parkingTicket.json";

    private FileJsonRW jsonRW = new FileJsonRW();
    private List<ParkingTicket> parkingTickets = jsonRW.jsonReadParkingTicketsFromFile();

    public ParkingTicketDbManagerImpl() {
    }

    @Override
    public void addTicketToDb(ParkingTicket parkingTicket) {
        parkingTickets = getParkingTicketsDb();
        parkingTicket.setTicketID(getLastTicketIdInDb() + 1);
        parkingTickets.add(parkingTicket);
        writeTicketsDbToFile(parkingTickets);
    }

    @Override
    public List<ParkingTicket> getParkingTicketsDb() {
        return jsonRW.jsonReadParkingTicketsFromFile();
    }

    @Override
    public List<ParkingTicket> getAllUsersParkingTickets(User user) {
        return getParkingTicketsDb().stream()
                .filter(ticket -> ticket.getRegularUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public void rewriteParkingTicketDetailsToFile(ParkingTicket ticketWithNewDetails) {
        parkingTickets = getParkingTicketsDb();
        for (ParkingTicket ticket : parkingTickets) {
            if (ticket.equals(ticketWithNewDetails)) {
                ticket.setEndParking(ticketWithNewDetails.getEndParking());
                ticket.setTicketAmount(ticketWithNewDetails.getTicketAmount());
                ticket.setRecordStatus(ticketWithNewDetails.getRecordStatus());
                writeTicketsDbToFile(parkingTickets);
            }
        }
    }

    private long getLastTicketIdInDb() {
        return getParkingTicketsDb().get(getParkingTicketsDb().size() - 1).getTicketID();
    }

    private void writeTicketsDbToFile(List<ParkingTicket> parkingTicketList) {
        jsonRW.jsonWriteObjectListToFile(parkingTicketList, PATH_TICKET_LIST);
    }
}