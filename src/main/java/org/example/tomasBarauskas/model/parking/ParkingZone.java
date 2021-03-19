package org.example.tomasBarauskas.model.parking;

import java.math.BigDecimal;

public enum ParkingZone implements Nameable{
    VILNIUS_BLUE_ZONE(new BigDecimal(6),new BigDecimal(50), "Melina zona"),
    VILNIUS_RED_ZONE(new BigDecimal(4),new BigDecimal(30), "Raudonas zona"),
    VILNIUS_GREEN_ZONE(new BigDecimal(1),new BigDecimal(10),"Zalia zona"),
    KAUNAS_RED_ZONE(new BigDecimal(3),new BigDecimal(25), "Raudonas zona"),
    KAUNAS_BLUE_ZONE(new BigDecimal(4),new BigDecimal(40), "Melina zona"),
    KAUNAS_GREEN_ZONE(new BigDecimal(1),new BigDecimal(10), "Zalia zona"),
    KLAIPEDA_RED_ZONE(new BigDecimal(2),new BigDecimal(25), "Raudona zona"),
    KLAIPEDA_GREEN_ZONE(new BigDecimal(1),new BigDecimal(10), "Zalia zona"),
    NO_ZONE(new BigDecimal(0),new BigDecimal(0), "Sios zonos siame mieste dar nera");

    private BigDecimal costPerHour;
    private BigDecimal fine;
    private String name;

    ParkingZone(BigDecimal costPerHour, BigDecimal fine, String name) {
        this.costPerHour = costPerHour;
        this.fine = fine;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ParkingZone{" +
                "costPerHour=" + costPerHour +
                ", penalty=" + fine +
                "} " + super.toString();
    }

    public BigDecimal getCostPerHour() {
        return costPerHour;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public String getName() {
        return name;
    }

    public void setCostPerHour(BigDecimal costPerHour) {
        this.costPerHour = costPerHour;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }
}
