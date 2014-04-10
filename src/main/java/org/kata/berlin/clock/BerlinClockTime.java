package org.kata.berlin.clock;


public class BerlinClockTime {
    private Lamp topLamp;

    public BerlinClockTime(Lamp topLamp) {
        if (topLamp == null) {
            throw new IllegalArgumentException("Lamp for seconds must be defined.");
        }
        this.topLamp = topLamp;
    }

    public Lamp getTopLamp() {
        return topLamp;
    }

    public String toStringRepresentation() {
        return topLamp.toStringRepresentation() + "\n";
//                +
//                "RRRR\n" +
//                "RRRR\n" +
//                "OOOOOOOOOOO\n" +
//                "OOOO";
    }

}
