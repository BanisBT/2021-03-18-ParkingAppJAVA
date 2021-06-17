package org.example.tomasBarauskas.service.parkingFineDbManager;

import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingFine;
import org.example.tomasBarauskas.model.user.User;

import java.util.List;

public interface ParkingFineDbManager {

    void addParkingFineToDb(ParkingFine fine);

    List<ParkingFine> getParkingFinesDb();

    List<ParkingFine> getUsersParkingFines(User user);

    void probabilityGetParkingFineFromController();
}