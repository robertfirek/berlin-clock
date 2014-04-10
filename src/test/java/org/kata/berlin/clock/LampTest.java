package org.kata.berlin.clock;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LampTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void canBeSwitchedOffOrSwitchedOn() throws Exception {
        Lamp switchedOffLamp = new Lamp(Lamp.State.OFF);
        Lamp switchedOnLamp = new Lamp(Lamp.State.YELLOW);

        assertThat(switchedOffLamp.getState(), is(Lamp.State.OFF));
        assertThat(switchedOnLamp.getState(), is(Lamp.State.YELLOW));
    }

    @Test
    public void mustHaveState() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("State must be defined."));

        new Lamp(null);
    }

    @Test
    public void shouldDefineStringRepresentationForEachState() throws Exception {
        Lamp switchedOffLamp = new Lamp(Lamp.State.OFF);
        Lamp switchedOnLamp = new Lamp(Lamp.State.YELLOW);

        assertThat(switchedOffLamp.toStringRepresentation(), is("O"));
        assertThat(switchedOnLamp.toStringRepresentation(), is("Y"));
    }
}
