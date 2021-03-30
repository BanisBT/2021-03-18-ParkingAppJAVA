package org.example.tomasBarauskas.factory.parkingFineFactory;

import org.example.tomasBarauskas.exception.finance.NotPaidParkingFine;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingRecordStatus;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingFine;
import org.example.tomasBarauskas.model.user.User;
import org.example.tomasBarauskas.service.parkingFineDbManager.ParkingFineDbManager;
import org.example.tomasBarauskas.service.parkingFineDbManager.ParkingFineDbManagerImpl;

import java.util.List;

public class ParkingFineFactory {
    private ParkingFineDbManager fineDb = new ParkingFineDbManagerImpl();

    public void checkForUserUnpaidParkingFine(User user) throws NotPaidParkingFine {
        List<ParkingFine> userParkingFinesFromDb = fineDb.getUsersParkingFines(user);
        for (ParkingFine fine : userParkingFinesFromDb) {
            if (fine.getRecordStatus().equals(ParkingRecordStatus.UNPAID)) {
                throw new NotPaidParkingFine();
            }
        }
    }
}
