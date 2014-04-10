package org.kata.berlin.clock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BerlinClockTest {

    @Test
    public void shouldTakeClassicHourRepresentationAndChangeToBerlinClockRepresentation() throws Exception {
        String classicHourRepresentation = "24::00:00";
        BerlinClock berlinClock = new BerlinClock();

        BerlinClockTime berlinClockTime = berlinClock.getBerlinClockTime(classicHourRepresentation);

        assertThat(berlinClockTime.toStringRepresentation(), is("" +
                "Y\n" +
                "RRRR\n" +
                "RRRR\n" +
                "OOOOOOOOOOO\n" +
                "OOOO"));
    }
}
