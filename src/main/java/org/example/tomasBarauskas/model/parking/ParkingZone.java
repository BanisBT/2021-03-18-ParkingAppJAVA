package org.example.tomasBarauskas.model.parking;

import java.io.Serializable;
import java.math.BigDecimal;

public enum ParkingZone implements Nameable, Serializable {
    VILNIUS_BLUE_ZONE(new BigDecimal(6), new BigDecimal(50), "Vilnius: Melina zona"),
    VILNIUS_RED_ZONE(new BigDecimal(4), new BigDecimal(30), "Vilnius: Raudonas zona"),
    VILNIUS_GREEN_ZONE(new BigDecimal(1), new BigDecimal(10), "Vilnius: Zalia zona"),
    KAUNAS_RED_ZONE(new BigDecimal(3), new BigDecimal(25), "Kaunas: Raudonas zona"),
    KAUNAS_BLUE_ZONE(new BigDecimal(4), new BigDecimal(40), "Kaunas: Melina zona"),
    KAUNAS_GREEN_ZONE(new BigDecimal(1), new BigDecimal(10), "Kaunas: Zalia zona"),
    KLAIPEDA_RED_ZONE(new BigDecimal(2), new BigDecimal(25), "Klaipeda: Raudona zona"),
    KLAIPEDA_GREEN_ZONE(new BigDecimal(1), new BigDecimal(10), "Klaipeda: Zalia zona"),
    NO_ZONE(new BigDecimal(0), new BigDecimal(0), "Sios zonos siame mieste dar nera");

    private BigDecimal costPerHour;
    private BigDecimal fine;
    private final String name;

    ParkingZone(BigDecimal costPerHour, BigDecimal fine, String name) {
        this.costPerHour = costPerHour;
        this.fine = fine;
        this.name = name;
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

    @Override
    public String toString() {
        return "Parkavimo zona - " + super.toString() +
                " kaina: " + costPerHour +
                " baudos dydis" + fine;
    }

    public String toStringForPerHourPriceChange() {
        return name + " valandine kaina yra " + costPerHour + " Eur";
    }

    public String toStringForFinePriceChange() {
        return name + " baudos dydis yra " + fine + " Eur";
    }
}
