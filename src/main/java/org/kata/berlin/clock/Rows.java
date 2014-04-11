package org.kata.berlin.clock;


public class Rows {
    private Row firstRow;
    private Row secondRow;

    public Rows(Row firstRow, Row secondRow) {
        if (firstRow == null) {
            throw new IllegalArgumentException("First row must be defined.");
        }
        if (secondRow == null) {
            throw new IllegalArgumentException("Second row must be defined.");
        }
        this.firstRow = firstRow;
        this.secondRow = secondRow;
    }

    public Row getFirstRow() {
        return firstRow;
    }

    public Row getSecondRow() {
        return secondRow;
    }

    public String toStringRepresentation() {
        return firstRow.toStringRepresentation() + "\n" + secondRow.toStringRepresentation();
    }
}
