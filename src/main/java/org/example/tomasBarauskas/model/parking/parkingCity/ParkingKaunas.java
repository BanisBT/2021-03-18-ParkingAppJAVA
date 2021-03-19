package org.example.tomasBarauskas.model.parking.parkingCity;

import org.example.tomasBarauskas.model.parking.ParkingZone;

public class ParkingKaunas implements ParkingCity{
    private static final ParkingZone BLUE_ZONE = ParkingZone.KAUNAS_BLUE_ZONE;
    private static final ParkingZone RED_ZONE = ParkingZone.KAUNAS_RED_ZONE;
    private static final ParkingZone GREEN_ZONE = ParkingZone.KAUNAS_GREEN_ZONE;
    private static final String NAME = "Kaunas";

    public ParkingKaunas() {
    }

    @Override
    public ParkingZone getBlueZone() {
        return BLUE_ZONE;
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
