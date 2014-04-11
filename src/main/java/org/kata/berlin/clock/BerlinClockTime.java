package org.kata.berlin.clock;


public class BerlinClockTime {
    private Lamp topLamp;
    private Rows hourRows;
    private Rows minuteRows;

    public BerlinClockTime(Lamp topLamp, Rows hourRows, Rows minuteRows) {
        if (topLamp == null) {
            throw new IllegalArgumentException("Lamp for seconds must be defined.");
        }
        if (hourRows == null) {
            throw new IllegalArgumentException("Hour rows must be defined.");
        }
        if (hourRows.getFirstRow().size() != 4) {
            throw new IllegalArgumentException("First hour row must have 4 lamps.");
        }
        if (hourRows.getSecondRow().size() != 4) {
            throw new IllegalArgumentException("Second hour row must have 4 lamps.");
        }
        if (minuteRows == null) {
            throw new IllegalArgumentException("Minute rows must be defined.");
        }
        if (minuteRows.getFirstRow().size() != 11) {
            throw new IllegalArgumentException("First minute row must have 11 lamps.");
        }
        if (minuteRows.getSecondRow().size() != 4) {
            throw new IllegalArgumentException("Second minute row must have 4 lamps.");
        }

        this.topLamp = topLamp;
        this.hourRows = hourRows;
        this.minuteRows = minuteRows;
    }

    public Lamp getTopLamp() {
        return topLamp;
    }


    public Rows getHourRows() {
        return hourRows;
    }


    public String toStringRepresentation() {
        return topLamp.toStringRepresentation() + "\n" +
                hourRows.toStringRepresentation() + "\n" +
                minuteRows.toStringRepresentation();
    }

    public Rows getMinuteRows() {
        return minuteRows;
    }
}
