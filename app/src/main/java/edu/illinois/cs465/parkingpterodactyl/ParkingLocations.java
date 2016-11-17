package edu.illinois.cs465.parkingpterodactyl;

/**
 * Created by Pranavi on 11/15/16.
 */

public class ParkingLocations {
    private String nameOfLoc;
    private int Latitude;
    private int Longitude;

    public ParkingLocations(String locationName, int LatitudeVal, int LongitudeVal) {
        nameOfLoc = locationName;
        Latitude = LatitudeVal;
        Longitude = LongitudeVal;
    }

    public String getNameOfLoc() {
        return nameOfLoc;
    }

    public int getLatitude() {
        return Latitude;
    }

    public int getLongitude() {
        return Longitude;
    }

    public String getNameForGoogleMaps() {
        String GoogleMapsAddress = "google.navigation:q=" + getLatitude() + "," + getLongitude();
        return GoogleMapsAddress;
    }

}
