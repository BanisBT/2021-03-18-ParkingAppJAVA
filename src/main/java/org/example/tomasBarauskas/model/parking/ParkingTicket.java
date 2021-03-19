package org.example.tomasBarauskas.model.parking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingTicket {
    private static long counter = 0;

    private long ticketID;
    private LocalDateTime dateTime;
    private String parkingCity;
    private ParkingZone parkingZone;
    private ParkingTime parkingTime;
    private String parkedCarNumber;

    public ParkingTicket() {
        this.ticketID = counter++;
    }

    public ParkingTicket(String parkingCity, ParkingZone parkingZone, ParkingTime parkingTime, String parkedCarNumber) {
        this();
        this.parkingCity = parkingCity;
        this.parkingZone = parkingZone;
        this.parkingTime = parkingTime;
        this.parkedCarNumber = parkedCarNumber;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "ParkingTicket " + '\n' +
                "ID | Miestas | Zona | Automobilio numeris | Data ir Laikas" + '\n' +
                ticketID + " | " + parkingCity + " | " + parkingZone.getName() + " | " + parkedCarNumber + " | " + dateTime.format(dtf) + '\n';
    }

    public ParkingZone getParkingZone() {
        return parkingZone;
    }

    public ParkingTime getParkingTime() {
        return parkingTime;
    }

    public String getParkingCity() {
        return parkingCity;
    }
}
