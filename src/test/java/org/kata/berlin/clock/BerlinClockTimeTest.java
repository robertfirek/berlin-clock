package org.kata.berlin.clock;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BerlinClockTimeTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldHaveTopLampToRepresentSeconds() throws Exception {
        BerlinClockTime berlinClockTimeSecondsOff = new BerlinClockTime(new Lamp(Lamp.State.OFF));
        BerlinClockTime berlinClockTimeSecondsOn = new BerlinClockTime(new Lamp(Lamp.State.YELLOW));

       assertThat(berlinClockTimeSecondsOff.getTopLamp().toStringRepresentation(), is("O"));
       assertThat(berlinClockTimeSecondsOn.getTopLamp().toStringRepresentation(), is("Y"));
    }

    @Test
    public void mustHaveLampForSeconds() throws Exception{
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Lamp for seconds must be defined."));

        new BerlinClockTime(null);
    }


}
