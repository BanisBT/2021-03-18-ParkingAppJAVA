package org.example.tomasBarauskas.model.parking.parkingCity;

import org.example.tomasBarauskas.model.parking.ParkingZone;

public class ParkingKlaipeda implements ParkingCity{
    private static final ParkingZone NO_ZONE = ParkingZone.NO_ZONE;
    private static final ParkingZone RED_ZONE = ParkingZone.KLAIPEDA_RED_ZONE;
    private static final ParkingZone GREEN_ZONE = ParkingZone.KLAIPEDA_GREEN_ZONE;
    private static final String NAME = "Klaipeda";

    public ParkingKlaipeda() {
    }

    @Override
    public ParkingZone getBlueZone() {
        return NO_ZONE;
    }

    @Override
    public ParkingZone getRedZone() {
        return RED_ZONE;
    }

    @Override
    public ParkingZone getGreenZone() {
        return GREEN_ZONE;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
