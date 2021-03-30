package org.example.tomasBarauskas.model.parking;

import java.io.Serializable;
import java.math.BigDecimal;

public enum ParkingTime implements Nameable, Serializable {
    HALF_HOUR(new BigDecimal("0.5"), "Puse valandos"),
    ONE_HOUR(new BigDecimal(1), "Valanda"),
    TWO_HOUR(new BigDecimal(2), "Dvi valandos");

    private BigDecimal hourInDoubleValue;
    private String name;

    ParkingTime(BigDecimal hourInDoubleValue, String name) {
        this.hourInDoubleValue = hourInDoubleValue;
        this.name = name;
    }

    public BigDecimal getHourInDoubleValue() {
        return hourInDoubleValue;
    }

    @Override
    public String getName() {
        return null;
    }
}
