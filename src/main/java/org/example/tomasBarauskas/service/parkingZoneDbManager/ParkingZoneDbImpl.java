package org.example.tomasBarauskas.service.parkingZoneDbManager;

import org.example.tomasBarauskas.model.parking.ParkingZone;

import java.util.ArrayList;
import java.util.List;

public class ParkingZoneDbImpl implements ParkingZoneDb{
    private List<ParkingZone> parkingZoneList = new ArrayList<>();

    public ParkingZoneDbImpl() {
    }

    public List<ParkingZone> getParkingZoneList() {
        parkingZoneList.clear();

        parkingZoneList.add(ParkingZone.VILNIUS_GREEN_ZONE);
        parkingZoneList.add(ParkingZone.VILNIUS_BLUE_ZONE);
        parkingZoneList.add(ParkingZone.VILNIUS_RED_ZONE);
        parkingZoneList.add(ParkingZone.KAUNAS_BLUE_ZONE);
        parkingZoneList.add(ParkingZone.KAUNAS_GREEN_ZONE);
        parkingZoneList.add(ParkingZone.KAUNAS_RED_ZONE);
        parkingZoneList.add(ParkingZone.KLAIPEDA_GREEN_ZONE);
        parkingZoneList.add(ParkingZone.KLAIPEDA_RED_ZONE);

        return parkingZoneList;
    }
}
