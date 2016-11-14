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
        // TODO - Compare the current time to the message creation time and return the appropriate string
        return "now";
    }
}
