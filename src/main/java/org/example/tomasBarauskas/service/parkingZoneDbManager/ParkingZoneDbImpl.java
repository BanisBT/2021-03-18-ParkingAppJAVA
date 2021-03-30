package org.example.tomasBarauskas.service.parkingZoneDbManager;

import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.util.FileRW;
import org.example.tomasBarauskas.util.ParkingZoneFileRW;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParkingZoneDbImpl implements ParkingZoneDb {
    private static final String PATH_ZONE_DB = "/Users/Gabi/IdeaProjects/2021-03-10/2021-03-19-ParkingAppDarbas/src/main/java/org/example/tomasBarauskas/file/ParkingZoneDatabase.ser";

    private FileRW zoneFileRW = new ParkingZoneFileRW();
    private List<ParkingZone> parkingZoneList = new ArrayList<>();

    public ParkingZoneDbImpl() {
        try {
            parkingZoneList = zoneFileRW.getDetailsFromFile1(PATH_ZONE_DB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ParkingZone> getParkingZoneList(){
        try {
            parkingZoneList = zoneFileRW.getDetailsFromFile1(PATH_ZONE_DB);
        } catch (IOException e) {
            System.out.println("Klaida");
        }
        return parkingZoneList;
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
        try {
            zoneFileRW.writeObjectDetailsToFile(PATH_ZONE_DB, zoneList);
        } catch (IOException e) {
            System.out.println("Klaida");
        }
    }
}
