package edu.illinois.cs465.parkingpterodactyl;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Pranavi on 11/15/16.
 */

public class ParkingLocations {
    public enum carSize {SMALL, MEDIUM, LARGE, EXTRA_LARGE}
    public enum parkingType {FREE, PAID, STREET, EVENT}

    private String nameOfLoc;
    private double latitude;
    private double longitude;
    private carSize maxSize;
    private parkingType type;

    public ParkingLocations(String locationName, double latitudeVal, double longitudeVal,
                            carSize maxCarSize, parkingType spotType) {
        nameOfLoc = locationName;
        latitude = latitudeVal;
        longitude = longitudeVal;
        maxSize = maxCarSize;
        type = spotType;
    }

    public String getNameOfLoc() {
        return nameOfLoc;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public parkingType getType() { return type; }

    public String getNameForGoogleMaps() {
        return "google.navigation:q=" + getLatitude() + "," + getLongitude();
    }

    public boolean spotBigEnough(carSize testSize) {
        switch(testSize) {
            case SMALL:
                return true;
            case MEDIUM:
                if (maxSize != carSize.SMALL) {
                    return true;
                }
                break;
            case LARGE:
                if (maxSize == carSize.LARGE || maxSize == carSize.EXTRA_LARGE) {
                    return true;
                }
                break;
            case EXTRA_LARGE:
                if (maxSize == carSize.EXTRA_LARGE) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    public LatLng getLatLng() {
        return new LatLng(this.latitude, this.longitude);
    }

}
