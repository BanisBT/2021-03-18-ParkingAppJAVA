package org.example.tomasBarauskas.util.file_rw;

import org.example.tomasBarauskas.model.parking.ParkingZone;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ParkingZoneFileRW implements FileRW {

    @Override
    public List getDetailsFromFile1(String path) throws IOException {
        List<ParkingZone> parkingZoneDetailsFromFile = new ArrayList<>();
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream oos = new ObjectInputStream(fis);
        Object obj = null;

        try {
            while ((obj = oos.readObject()) != null){
                if (obj instanceof ParkingZone){
                    parkingZoneDetailsFromFile.add((ParkingZone) obj);
                }
            }
        } catch (EOFException | ClassNotFoundException e){

        }
        return parkingZoneDetailsFromFile;
    }
}