package org.example.tomasBarauskas.model.parking.parkingRecord;

import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingFine extends ParkingRecord {
    private static long counter = 0;

    private final long fineID;
    private final LocalDateTime fineDateTime;
    private BigDecimal fineAmount;

    public ParkingFine(String parkingCity, ParkingZone parkingZone, User user, LocalDateTime fineDateTime) {
        super(parkingCity, parkingZone, user);
        this.fineID = counter++;
        this.fineDateTime = fineDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Bauda: " + '\n' +
                "Numeris | Baudos dydis | Data ir Laikas" + '\n' +
                fineID + " | " + fineDateTime + " | " + fineAmount + '\n' +
                super.toString();
    }
}
