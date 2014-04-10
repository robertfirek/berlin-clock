package org.kata.berlin.clock;


public class Time {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        if (hours < 0 || hours > 24) {
            throw new IllegalArgumentException("Hour must be between 0 and 24.");
        }
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59.");
        }
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Seconds must be between 0 and 59.");
        }
        if (hours == 24 && (minutes > 0 || seconds > 0)) {
            throw new IllegalArgumentException("Time cannot store time after 24::00:00.");
        }
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
