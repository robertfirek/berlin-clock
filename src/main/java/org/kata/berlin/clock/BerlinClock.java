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
        return new BerlinClockTime(getTopLamp(time), getHourRows(time));
    }


    private Lamp getTopLamp(Time time) {
        return new Lamp(getTopLampState(time));
    }

    private Lamp.State getTopLampState(Time time) {
        return time.getSeconds() % 2 == 0 ? Lamp.State.YELLOW : Lamp.State.OFF;
    }

    private Rows getHourRows(Time time) {
        int hours = time.getHours();
        int numberOfFirstRowLampOn = hours / 5;
        int numberOFSecondRowLampOn = hours % 5;
        return new Rows(
                new Row(getHourLamp(numberOfFirstRowLampOn, 1),
                        getHourLamp(numberOfFirstRowLampOn, 2),
                        getHourLamp(numberOfFirstRowLampOn, 3),
                        getHourLamp(numberOfFirstRowLampOn, 4)),
                new Row(getHourLamp(numberOFSecondRowLampOn, 1),
                        getHourLamp(numberOFSecondRowLampOn, 2),
                        getHourLamp(numberOFSecondRowLampOn, 3),
                        getHourLamp(numberOFSecondRowLampOn, 4)));
    }

    private Lamp getHourLamp(int numberOfLampSwitchOn, int lampNumber) {
        return numberOfLampSwitchOn >= lampNumber ? new Lamp(Lamp.State.RED) : new Lamp(Lamp.State.OFF);
    }


}
