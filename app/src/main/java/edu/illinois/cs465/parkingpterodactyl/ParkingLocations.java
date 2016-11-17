package edu.illinois.cs465.parkingpterodactyl;

/**
 * Created by Pranavi on 11/15/16.
 */

public class ParkingLocations {
    private String nameOfLoc;
    private String nameForMaps;

    public ParkingLocations(String locationName, String mapsDest) {
        nameOfLoc = locationName;
        nameForMaps = mapsDest;
    }

    public String getNameForGoogleMaps() {
        return nameForMaps;
    }

    public String getNameOfLoc() {
        return nameOfLoc;
    }

}
