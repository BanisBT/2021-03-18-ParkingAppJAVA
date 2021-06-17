package org.example.tomasBarauskas.factory.parkingZoneFactory;

import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.service.parkingZoneDbManager.ParkingZoneDb;
import org.example.tomasBarauskas.service.parkingZoneDbManager.ParkingZoneDbImpl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingZoneFactory {
    private ParkingZoneDb zoneDb = new ParkingZoneDbImpl();
    private List<ParkingZone> zoneListFromDb = zoneDb.getParkingZoneList();

    public ParkingZoneFactory() throws IOException {
    }

    public List<String> getZoneListWithPricePerHour(){
        return IntStream.range(0, zoneListFromDb.size() - 1)
                .mapToObj(index -> "ZonosNR. " +  index + " - " + zoneListFromDb.get(index).toStringForPerHourPriceChange())
                .collect(Collectors.toList());
    }

    public void changeZoneCostPerHour(int zoneNumber, float newPrice) {
        try {
            zoneDb.changeZoneCostPerHour(zoneNumber, newPrice);
        } catch (IOException e) {
            System.out.println("Klaida");
        }
    }

    public List<String> getListWithFineAmount(){
        return IntStream.range(0, zoneListFromDb.size() - 1)
                .mapToObj(index -> "ZonosNR. " + index + " - " + zoneListFromDb.get(index).toStringForFinePriceChange())
                .collect(Collectors.toList());
    }

    public void changeZoneFineAmount(int zoneNumber, float newFineAmount){
        zoneDb.changeZoneFineAmount(zoneNumber, newFineAmount);
    }
}