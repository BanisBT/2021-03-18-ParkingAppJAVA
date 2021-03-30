package org.example.tomasBarauskas.service.parkingZoneDbManager;

import org.example.tomasBarauskas.model.parking.ParkingZone;

import java.io.IOException;
import java.util.List;

public interface ParkingZoneDb {

    List<ParkingZone> getParkingZoneList() throws IOException;

    void changeZoneCostPerHour(int zoneNumber, float newPrice) throws IOException;

    void changeZoneFineAmount(int zoneNumber, float newFineAmount);
}
