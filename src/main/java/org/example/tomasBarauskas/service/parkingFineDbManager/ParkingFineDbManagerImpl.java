package org.example.tomasBarauskas.service.parkingFineDbManager;

import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingFine;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.util.json.FileJsonRW;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingFineDbManagerImpl implements ParkingFineDbManager{
    private final String PATH_USER_LIST = "target/users.json";

    private FileJsonRW jsonRW = new FileJsonRW();
    private List<ParkingFine> finesDb = jsonRW.jsonReadParkingFineFromFile();

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

    private void writeParkingFineToFile(List<ParkingFine> parkingFineList){
        jsonRW.jsonWriteObjectListToFile(parkingFineList,PATH_USER_LIST);
    }
}