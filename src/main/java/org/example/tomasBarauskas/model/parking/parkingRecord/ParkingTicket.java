package org.example.tomasBarauskas.model.parking.parkingRecord;

import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingRecordStatus;
import org.example.tomasBarauskas.model.user.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingTicket extends ParkingRecord implements Serializable {
    private static long counter = 0;

    private final long ticketID;
    private final LocalDateTime beginParking;
    private LocalDateTime endParking;
    private BigDecimal ticketAmount = new BigDecimal(0);

    public ParkingTicket(String parkingCity, ParkingZone parkingZone, User user) {
        super(parkingCity, parkingZone, user);
        super.setRecordStatus(ParkingRecordStatus.OPEN);
        this.ticketID = counter++;
        this.beginParking = LocalDateTime.now();
        this.endParking = LocalDateTime.now();

    }

    public long getTicketID() {
        return ticketID;
    }

    public LocalDateTime getBeginParking() {
        return beginParking;
    }

    public LocalDateTime getEndParking() {
        return endParking;
    }

    public BigDecimal getTicketAmount() {
        return ticketAmount;
    }

    public void setEndParking(LocalDateTime endParking) {
        this.endParking = endParking;
    }

    public void setTicketAmount(BigDecimal ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return super.toString() + "Bilieto numeris | Suma | Stovejimo laikas      nuo  /   iki" + '\n' +
                ticketID + " | " + ticketAmount + " | " + beginParking.format(dtf) + " | " + endParking.format(dtf) + '\n';
    }
}
