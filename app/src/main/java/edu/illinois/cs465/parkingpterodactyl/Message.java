package edu.illinois.cs465.parkingpterodactyl;

import java.util.Calendar;

/**
 * Created by Zach on 11/13/2016.
 */

public class Message {
    private String message;
    private Calendar time;

    public Message(String m) {
        message = m;
        time = Calendar.getInstance();
    }

    public String getText() {
        return message;
    }

    public String getPastTime() {
        Calendar currentTime = Calendar.getInstance();
        long currentMillis = currentTime.getTimeInMillis();
        long messageMillis = this.time.getTimeInMillis();

        long minuteDifference = (currentMillis - messageMillis) / 60000;
        long hourDifference = minuteDifference / 60;
        long dayDifference = hourDifference / 24;
        long weekDifference = dayDifference / 7;

        if (minuteDifference < 2) {
            return "now";
        } else if (minuteDifference < 60) {
            return "" + minuteDifference + "m";
        } else if (hourDifference < 24) {
            return "" + hourDifference + "h";
        } else if (dayDifference < 7) {
            return "" + dayDifference + "d";
        } else {
            return "" + weekDifference + "w";
        }
    }
}
