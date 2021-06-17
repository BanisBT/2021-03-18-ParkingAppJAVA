package org.example.tomasBarauskas.model.parking.parkingRecord;

import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.parking.ParkingRecordStatus;
import org.example.tomasBarauskas.model.user.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingTicket extends ParkingRecord implements Serializable {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private long ticketID;
    private LocalDateTime beginParking;
    private LocalDateTime endParking;
    private BigDecimal ticketAmount = new BigDecimal(0);

    public ParkingTicket() {
    }

    public ParkingTicket(String parkingCity, ParkingZone parkingZone, User user) {
        super(parkingCity, parkingZone, user);
        super.setRecordStatus(ParkingRecordStatus.OPEN);
        this.ticketID = 1000;
        this.beginParking = LocalDateTime.now();
        this.endParking = LocalDateTime.now();
    }

    public String toStringForUserViewTickets() {
        return "Bilieto numeris | Stovejimo zona | Suma | Laikas nuo  /  iki  " + '\n' +
                ticketID + " | " + super.getParkingZone().getName() + " | " + ticketAmount + " | " + beginParking.format(dtf) + " / " + endParking.format(dtf) + '\n';
    }

    public String toStringForStatistic() {
        return "Talono info - Miestas/Zona | Data | Masinos numeris | Statusas " + '\n' +
                super.getParkingZone().getName() + " | " + beginParking.format(dtf) + " | " + super.getRegularUser().getCarNumber() + " | " + super.getRecordStatus() + '\n';
    }

    public boolean beganParkingTicketDateIsAfterDate(LocalDate fromDate) {
        return beginParking.toLocalDate().isAfter(fromDate.minusDays(1));
    }

    public boolean beganParkingTicketDateIsBefore(LocalDate toDate) {
        return beginParking.toLocalDate().isBefore(toDate.plusDays(1));
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

    public void setTicketID(long ticketID) {
        this.ticketID = ticketID;
    }

    public void setBeginParking(LocalDateTime beginParking) {
        this.beginParking = beginParking;
    }

    @Override
    public boolean equals(Object obj) {
        return ticketID == ((ParkingTicket) obj).getTicketID();
    }

    @Override
    public String toString() {
        return super.toString() + "Bilieto numeris | Suma | Stovejimo laikas      nuo  /   iki" + '\n' +
                ticketID + " | " + ticketAmount + " | " + beginParking.format(dtf) + " | " + endParking.format(dtf) + '\n';
    }
}