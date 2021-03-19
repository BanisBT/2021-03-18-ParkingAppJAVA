package org.example.tomasBarauskas.model.user;

import org.example.tomasBarauskas.model.parking.ParkingFine;

import java.math.BigDecimal;

public class RegularUser extends User {
    private String carNumber;
    private BigDecimal count = new BigDecimal(0);
    private ParkingFine fine;

    public RegularUser(String userId, String password, String name, String surname, String carNumber) {
        super(userId, password, name, surname);
        this.carNumber = carNumber;
    }

    @Override
    public String toString() {
        return super.toString() + "Automobilio numeris | Saskaitos balansas | Baudu kiekis | " + '\n' +
                carNumber + " | " + count + " | " + fine + " | " + " \n ";
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }
}
