package org.example.tomasBarauskas.model.user;

import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingFine;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable {
    private final String userId;
    private final String password;
    private final String name;
    private final String surname;
    private UserRole role = UserRole.REGULAR_ROLE;
    private String carNumber = "xxx 999";
    private BigDecimal count = new BigDecimal(0);
    private ParkingFine parkingFine;

    public User(String userId, String password, String name, String surname) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public BigDecimal getCount() {
        return count;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                ", carNumber='" + carNumber + '\'' +
                ", count=" + count +
                ", parkingFine=" + parkingFine +
                '}';
    }
}
