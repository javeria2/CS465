package edu.illinois.cs465.parkingpterodactyl;

/**
 * Created by Pranavi on 11/15/16.
 */

public class ParkingLocations {
    private String nameOfLoc;
    private double Latitude;
    private double Longitude;

    public ParkingLocations(String locationName, double LatitudeVal, double LongitudeVal) {
        nameOfLoc = locationName;
        Latitude = LatitudeVal;
        Longitude = LongitudeVal;
    }

    public String getNameOfLoc() {
        return nameOfLoc;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public String getNameForGoogleMaps() {
        String GoogleMapsAddress = "google.navigation:q=" + getLatitude() + "," + getLongitude();
        return GoogleMapsAddress;
    }

}
