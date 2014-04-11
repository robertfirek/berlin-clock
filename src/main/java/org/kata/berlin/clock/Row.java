package org.kata.berlin.clock;

public class Row {
    private Lamp[] lamps;

    public Row(Lamp... lamps) {
        if (lamps == null) {
            throw new IllegalArgumentException("Lamps must be defined.");
        }
        this.lamps = lamps;
    }

    public int size() {
        return lamps.length;
    }

    public Lamp getLamp(int numberOfLamp) {
        if (numberOfLamp > lamps.length) {
            throw new IllegalArgumentException("Row contains only " + lamps.length + " lamps.");
        }
        return lamps[numberOfLamp - 1];
    }

    public String toStringRepresentation() {
        StringBuilder stringBuffer = new StringBuilder();
        for (Lamp lamp : lamps) {
            stringBuffer.append(lamp.toStringRepresentation());
        }
        return stringBuffer.toString();
    }

}
