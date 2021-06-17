package org.example.tomasBarauskas.service.parkingFineDbManager;

import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingFine;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.service.parkingZoneDbManager.ParkingZoneDb;
import org.example.tomasBarauskas.service.parkingZoneDbManager.ParkingZoneDbImpl;
import org.example.tomasBarauskas.service.userUserDbManager.UserDbManager;
import org.example.tomasBarauskas.service.userUserDbManager.UserDbManagerImpl;
import org.example.tomasBarauskas.util.json.FileJsonRW;
import org.example.tomas_barauskas.ParkingController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingFineDbManagerImpl implements ParkingFineDbManager {
    private final String PATH_USER_LIST = "target/users.json";
    private final String PARKING_FINE = "true";

    private final FileJsonRW jsonRW = new FileJsonRW();
    private List<ParkingFine> finesDb = jsonRW.jsonReadParkingFineFromFile();
    private final UserDbManager userDb = new UserDbManagerImpl();
    private final ParkingZoneDb zoneDb = new ParkingZoneDbImpl();

    public ParkingFineDbManagerImpl() {
    }

    @Override
    public void addParkingFineToDb(ParkingFine fine) {
        finesDb = jsonRW.jsonReadParkingFineFromFile();
        finesDb.add(fine);
        writeParkingFineToFile(finesDb);
    }

    @Override
    public List<ParkingFine> getParkingFinesDb() {
        return jsonRW.jsonReadParkingFineFromFile();
    }

    @Override
    public List<ParkingFine> getUsersParkingFines(User user) {
        return getParkingFinesDb().stream()
                .filter(fine -> fine.getRegularUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public void probabilityGetParkingFineFromController() {
        List<String> infoFromControllerStringList = parkingFineFromController();
        ParkingZone parkingZoneGotFine = zoneDb.getParkingZoneByName(infoFromControllerStringList.get(1));
        if (infoFromControllerStringList.get(0).equals(PARKING_FINE)) {
            ParkingFine tempParkingFine = new ParkingFine((zoneDb.getParkingCityByZone(parkingZoneGotFine)).getName(), parkingZoneGotFine, userDb.findUserByCarNumber(infoFromControllerStringList.get(2)), LocalDateTime.now());
            addParkingFineToDb(tempParkingFine);
        }
    }

    private List<String> parkingFineFromController() {
        ParkingController controller = new ParkingController();
        List<String> allParkingZonesNames = allParkingZonesNames();
        List<String> allCarNumbers = allCarNumbersOfUsers();

        return controller.parkingFineInfoFromController(allParkingZonesNames, allCarNumbers);
    }

    private void writeParkingFineToFile(List<ParkingFine> parkingFineList) {
        jsonRW.jsonWriteObjectListToFile(parkingFineList, PATH_USER_LIST);
    }

    private List<String> allCarNumbersOfUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            allUsers = userDb.getAllUsers();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nepavyko uzkrauti duomenu");
        }

        return allUsers.stream()
                .map(User::getCarNumber).collect(Collectors.toList());
    }

    private List<String> allParkingZonesNames() {
        List<ParkingZone> allZones = new ArrayList<>();
        try {
            allZones = zoneDb.getParkingZoneList();
        } catch (IOException e) {
            System.out.println("Nepavyko uzkrauti duomenu");
        }

        return allZones.stream()
                .map(ParkingZone::getName).collect(Collectors.toList());
    }
}