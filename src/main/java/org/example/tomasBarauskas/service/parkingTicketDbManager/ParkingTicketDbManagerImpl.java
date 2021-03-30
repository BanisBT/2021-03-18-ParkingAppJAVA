package org.example.tomasBarauskas.service.parkingTicketDbManager;

import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.util.FileRW;
import org.example.tomasBarauskas.util.ParkingTicketsFileRW;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingTicketDbManagerImpl implements ParkingTicketDbManager {
    private final String PATH_TICKET_DB_FILE = "/Users/Gabi/IdeaProjects/2021-03-10/2021-03-19-ParkingAppDarbas/src/main/java/org/example/tomasBarauskas/file/ParkingTicketDatabase.ser";

    private FileRW ticketFileRW = new ParkingTicketsFileRW();
    private static List<ParkingTicket> parkingTickets = new ArrayList<>();

    public ParkingTicketDbManagerImpl() {
        try {
            parkingTickets = ticketFileRW.getDetailsFromFile1(PATH_TICKET_DB_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTicketToDb(ParkingTicket parkingTicket) {
        try {
            parkingTickets = ticketFileRW.getDetailsFromFile1(PATH_TICKET_DB_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        parkingTickets.add(parkingTicket);
        writeTicketDbToFile();
    }

    @Override
    public List<ParkingTicket> getParkingTicketsDb() {
        try {
            parkingTickets = ticketFileRW.getDetailsFromFile1(PATH_TICKET_DB_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parkingTickets;
    }

    @Override
    public List<ParkingTicket> getAllUsersParkingTickets(User user) {
       return getParkingTicketsDb().stream()
                .filter(ticket -> ticket.getRegularUser().equals(user))
                .collect(Collectors.toList());
    }

    private void writeTicketDbToFile() {
        try {
            ticketFileRW.writeObjectDetailsToFile(PATH_TICKET_DB_FILE, parkingTickets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
