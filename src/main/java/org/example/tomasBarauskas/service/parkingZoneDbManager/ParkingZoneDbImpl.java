package org.example.tomasBarauskas.service.parkingZoneDbManager;

import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingCity;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingKaunas;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingKlaipeda;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingVilnius;
import org.example.tomasBarauskas.util.json.FileJsonRW;

import java.math.BigDecimal;
import java.util.List;

public class ParkingZoneDbImpl implements ParkingZoneDb {
    private final String PATH_ZONE_LIST = "target/parkingZone.json";

    private FileJsonRW jsonRW = new FileJsonRW();
    private List<ParkingZone> parkingZoneList = jsonRW.jsonReadParkingZoneFromFile();

    public ParkingZoneDbImpl() {
    }

    public List<ParkingZone> getParkingZoneList() {
        return jsonRW.jsonReadParkingZoneFromFile();
    }

    @Override
    public void changeZoneCostPerHour(int zoneNumber, float newPrice) {
        parkingZoneList = getParkingZoneList();
        BigDecimal zonePricePerHourNewBidDecimal = BigDecimal.valueOf(newPrice);
        parkingZoneList.get(zoneNumber).setCostPerHour(zonePricePerHourNewBidDecimal);
        writeZoneToDb(parkingZoneList);
    }

    @Override
    public void changeZoneFineAmount(int zoneNumber, float newFineAmount) {
        parkingZoneList = getParkingZoneList();
        BigDecimal zoneFineAmountNewBigD = BigDecimal.valueOf(newFineAmount);
        parkingZoneList.get(zoneNumber).setFine(zoneFineAmountNewBigD);
        writeZoneToDb(parkingZoneList);
    }

    @Override
    public ParkingCity getParkingCityByZone(ParkingZone parkingZone) {
        if (parkingZone.equals(ParkingZone.VILNIUS_RED_ZONE) | parkingZone.equals(ParkingZone.VILNIUS_BLUE_ZONE) | parkingZone.equals(ParkingZone.VILNIUS_BLUE_ZONE)) {
            return new ParkingVilnius();
        }
        if (parkingZone.equals(ParkingZone.KAUNAS_RED_ZONE) | parkingZone.equals(ParkingZone.KAUNAS_GREEN_ZONE) | parkingZone.equals(ParkingZone.KAUNAS_GREEN_ZONE)) {
            return new ParkingKaunas();
        }
        if (parkingZone.equals(ParkingZone.KLAIPEDA_GREEN_ZONE) | parkingZone.equals(ParkingZone.KLAIPEDA_GREEN_ZONE)) {
            return new ParkingKlaipeda();
        }
        return null;
    }

    @Override
    public ParkingZone getParkingZoneByName(String parkingZoneName) {
        parkingZoneList = getParkingZoneList();
        for (ParkingZone zone : parkingZoneList) {
            if (zone.equals(parkingZoneName)) {
                return zone;
            }
        }
        return null;
    }

    private void writeZoneToDb(List zoneList) {
        jsonRW.jsonWriteObjectListToFile(zoneList, PATH_ZONE_LIST);
    }
}