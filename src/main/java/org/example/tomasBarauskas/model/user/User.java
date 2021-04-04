package org.example.tomasBarauskas.model.user;

import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingFine;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable {
    private String userId;
    private String password;
    private String name;
    private String surname;
    private UserRole role = UserRole.REGULAR_ROLE;
    private String carNumber = "xxx 999";
    private BigDecimal count = new BigDecimal(0);
    private ParkingFine parkingFine;

    public User() {
    }

    public User(String userId, String password, String name, String surname) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public ParkingFine getParkingFine() {
        return parkingFine;
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
    public boolean equals(Object obj) {
        return userId.equals(((User) obj).userId);
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