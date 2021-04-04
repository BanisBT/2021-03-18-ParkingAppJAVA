package org.example.tomasBarauskas.model.parking.parkingRecord;

import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.parking.ParkingRecordStatus;
import org.example.tomasBarauskas.model.user.User;

import java.io.Serializable;

public class ParkingRecord implements Serializable {
    private String parkingCity;
    private User regularUser;
    private ParkingZone parkingZone;
    private ParkingRecordStatus recordStatus = ParkingRecordStatus.UNPAID;

    public ParkingRecord() {
    }

    public ParkingRecord(String parkingCity, ParkingZone parkingZone, User user) {
        this.parkingCity = parkingCity;
        this.parkingZone = parkingZone;
        this.regularUser = user;
    }

    public String getParkingCity() {
        return parkingCity;
    }

    public ParkingZone getParkingZone() {
        return parkingZone;
    }

    public User getRegularUser() {
        return regularUser;
    }

    public ParkingRecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(ParkingRecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    @Override
    public String toString() {
        return "ParkingRecord{" +
                "parkingCity='" + parkingCity + '\'' +
                ", regularUser=" + regularUser +
                ", parkingZone=" + parkingZone +
                ", recordStatus=" + recordStatus +
                '}';
    }
}