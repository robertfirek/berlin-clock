package org.kata.berlin.clock;


public class BerlinClock {

    private HourFormatter hourFormatter;

    public BerlinClock(HourFormatter hourFormatter) {
        if (hourFormatter == null) {
            throw new IllegalArgumentException("Hour formatter must be defined.");
        }
        this.hourFormatter = hourFormatter;
    }

    public BerlinClockTime getBerlinClockTime(String classicHourRepresentation) {
        Time time = hourFormatter.splitTime(classicHourRepresentation);
        return new BerlinClockTime(new Lamp(getTopLampState(time)));
    }

    private Lamp.State getTopLampState(Time time) {
        return time.getSeconds() % 2 == 0 ? Lamp.State.YELLOW : Lamp.State.OFF;
    }

}
