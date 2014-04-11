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
        return new BerlinClockTime(getTopLamp(time), getHourRows(time), getMinuteHours(time));
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
                new Row(getLamp(numberOfFirstRowLampOn, Lamp.State.RED, 1),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.RED, 2),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.RED, 3),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.RED, 4)),
                new Row(getLamp(numberOFSecondRowLampOn, Lamp.State.RED, 1),
                        getLamp(numberOFSecondRowLampOn, Lamp.State.RED, 2),
                        getLamp(numberOFSecondRowLampOn, Lamp.State.RED, 3),
                        getLamp(numberOFSecondRowLampOn, Lamp.State.RED, 4)));
    }

    private Rows getMinuteHours(Time time) {
        int minutes = time.getMinutes();
        int numberOfFirstRowLampOn = minutes / 5;
        int numberOFSecondRowLampOn = minutes % 5;
        return new Rows(
                new Row(getLamp(numberOfFirstRowLampOn, Lamp.State.YELLOW, 1),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.YELLOW, 2),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.RED, 3),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.YELLOW, 4),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.YELLOW, 5),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.RED, 6),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.YELLOW, 7),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.YELLOW, 8),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.RED, 9),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.YELLOW, 10),
                        getLamp(numberOfFirstRowLampOn, Lamp.State.YELLOW, 11)),
                new Row(getLamp(numberOFSecondRowLampOn, Lamp.State.YELLOW, 1),
                        getLamp(numberOFSecondRowLampOn, Lamp.State.YELLOW, 2),
                        getLamp(numberOFSecondRowLampOn, Lamp.State.YELLOW, 3),
                        getLamp(numberOFSecondRowLampOn, Lamp.State.YELLOW, 4)));
    }

    private Lamp getLamp(int numberOfLampSwitchOn, Lamp.State switchOnState, int lampNumber) {
        return numberOfLampSwitchOn >= lampNumber ? new Lamp(switchOnState) : new Lamp(Lamp.State.OFF);
    }
}
