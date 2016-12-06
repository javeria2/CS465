package edu.illinois.cs465.parkingpterodactyl;

import java.util.Calendar;


/**
 * Class for storing the contents and creation time of a message board message
 */
public class Message {
    private String message;
    private Calendar time;

    // Constructor that takes the message text and sets the creation time to the current time
    public Message(String m) {
        message = m;
        time = Calendar.getInstance();
    }

    // Get the text fot the message
    public String getText() {
        return message;
    }

    // Get the text representing how long ago the message was created
    public String getPastTime() {
        Calendar currentTime = Calendar.getInstance();
        long currentMillis = currentTime.getTimeInMillis();
        long messageMillis = this.time.getTimeInMillis();

        long minuteDifference = (currentMillis - messageMillis) / 60000;
        long hourDifference = minuteDifference / 60;
        long dayDifference = hourDifference / 24;
        long weekDifference = dayDifference / 7;

        if (minuteDifference < 1) {
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
