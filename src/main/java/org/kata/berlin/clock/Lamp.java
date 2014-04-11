package org.kata.berlin.clock;


public class Lamp {

    public String toStringRepresentation() {
        return state.getStringRepresentation();
    }

    public enum State {
        YELLOW("Y"), OFF("O"), RED("R");

        private String stringRepresentation;

        public String getStringRepresentation() {
            return stringRepresentation;
        }

        private State(String stringRepresentation) {
            this.stringRepresentation = stringRepresentation;
        }
    }

    private State state;

    public Lamp(State state) {
        if (state == null) {
            throw new IllegalArgumentException("State must be defined.");
        }
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
