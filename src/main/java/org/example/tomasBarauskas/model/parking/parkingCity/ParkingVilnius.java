package org.example.tomasBarauskas.model.parking.parkingCity;

import org.example.tomasBarauskas.model.parking.ParkingZone;

public class ParkingVilnius implements ParkingCity{
    private static final ParkingZone BLUE_ZONE = ParkingZone.VILNIUS_BLUE_ZONE;
    private static final ParkingZone RED_ZONE = ParkingZone.VILNIUS_RED_ZONE;
    private static final ParkingZone GREEN_ZONE = ParkingZone.VILNIUS_GREEN_ZONE;
    private static final String NAME = "Vilnius";

    public ParkingVilnius() {
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