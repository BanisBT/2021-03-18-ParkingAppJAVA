package org.example.tomasBarauskas.util;

import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingTicketsFileRW implements FileRW{

    @Override
    public List getDetailsFromFile1(String path) throws IOException {
        List<ParkingTicket> tickets = new ArrayList<>();
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = null;

        try {
            while ((obj = ois.readObject()) != null){
                if (obj instanceof ParkingTicket){
                    tickets.add((ParkingTicket) obj);
                }
            }
        } catch (EOFException | ClassNotFoundException e){
            // failo pabaiga
        }
        return tickets;
    }
}
