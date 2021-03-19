package org.example.tomasBarauskas.model.parking.parkingCity;

import org.example.tomasBarauskas.model.parking.Nameable;
import org.example.tomasBarauskas.model.parking.ParkingZone;

public interface ParkingCity extends Nameable {
    ParkingZone getBlueZone();
    ParkingZone getRedZone();
    ParkingZone getGreenZone();
    String getName();
}
