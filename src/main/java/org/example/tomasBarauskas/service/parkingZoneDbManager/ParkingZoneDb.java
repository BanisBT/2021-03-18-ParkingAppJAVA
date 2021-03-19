package org.example.tomasBarauskas.service.parkingZoneDbManager;

import org.example.tomasBarauskas.model.parking.ParkingZone;

import java.util.List;

public interface ParkingZoneDb {
    List<ParkingZone> getParkingZoneList();
}
