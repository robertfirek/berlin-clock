package org.kata.berlin.clock;


public class BerlinClockTime {
    private Lamp topLamp;
    private Rows hourRows;

    public BerlinClockTime(Lamp topLamp, Rows hourRows) {
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
        this.topLamp = topLamp;
        this.hourRows = hourRows;
    }

    public Lamp getTopLamp() {
        return topLamp;
    }

    public String toStringRepresentation() {
        return topLamp.toStringRepresentation() + "\n" +
                hourRows.toStringRepresentation();
//                "OOOOOOOOOOO\n" +
//                "OOOO";
    }

    public Rows getHourRows() {
        return hourRows;
    }
}
