package org.example.tomasBarauskas.service.parkingFineDbManager;

import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingFine;
import org.example.tomasBarauskas.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingFineDbManagerImpl implements ParkingFineDbManager{
    private List<ParkingFine> finesDb = new ArrayList<>();

    public ParkingFineDbManagerImpl() {
    }

    @Override
    public void addParkingFineToDb(ParkingFine fine) {
        finesDb.add(fine);
    }

    @Override
    public List<ParkingFine> getParkingFinesDb() {
        return finesDb;
    }

    @Override
    public List<ParkingFine> getUsersParkingFines(User user) {
        return getParkingFinesDb().stream()
                .filter(fine -> fine.getRegularUser().equals(user))
                .collect(Collectors.toList());
    }
}
