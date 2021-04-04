package org.example.tomasBarauskas.service.parkingZoneDbManager;

import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.util.json.FileJsonRW;

import java.math.BigDecimal;
import java.util.List;

public class ParkingZoneDbImpl implements ParkingZoneDb {
    private final String PATH_ZONE_LIST = "target/parkingZone.json";

    private FileJsonRW jsonRW = new FileJsonRW();
    private List<ParkingZone> parkingZoneList = jsonRW.jsonReadParkingZoneFromFile();

    public ParkingZoneDbImpl() {
    }

    public List<ParkingZone> getParkingZoneList(){
        return jsonRW.jsonReadParkingZoneFromFile();
    }

    @Override
    public void changeZoneCostPerHour(int zoneNumber, float newPrice){
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

    private void writeZoneToDb(List zoneList){
        jsonRW.jsonWriteObjectListToFile(zoneList,PATH_ZONE_LIST);
    }
}