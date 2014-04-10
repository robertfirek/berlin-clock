package org.kata.berlin.clock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BerlinClockTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldTakeClassicHourRepresentationAndChangeToBerlinClockRepresentation() throws Exception {
        String classicHourRepresentationMidnight = "24::00:00";
        String classicHourRepresentationRandom = "13::32:33";
        BerlinClock berlinClock = new BerlinClock(new HourFormatter());

        BerlinClockTime berlinClockTimeMidnight = berlinClock.getBerlinClockTime(classicHourRepresentationMidnight);
        BerlinClockTime berlinClockTimeRandom = berlinClock.getBerlinClockTime(classicHourRepresentationRandom);

        assertThat(berlinClockTimeMidnight.toStringRepresentation(), is("" +
                "Y\n"
//                +
//                "RRRR\n" +
//                "RRRR\n" +
//                "OOOOOOOOOOO\n" +
//                "OOOO"
        ));
        assertThat(berlinClockTimeRandom.toStringRepresentation(), is("" +
                "O\n"
//                +
//                "RROO\n" +
//                "RRRO\n" +
//                "YYRYYROOOO\n" +
//                "YYOO"
        ));
    }

    @Test
    public void mustHaveHourFormatter() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Hour formatter must be defined."));

        new BerlinClock(null);
    }

    @Test
    public void shouldSwitchOnOffTopRowEveryTwoSeconds() throws Exception {
        String hourWithEvenSecond = "00::00:00";
        String hourWithOddSecond = "00::00:01";
        String hourWithAnotherEvenSecond = "00::00:34";
        String hourWithAnotherOddSecond = "00::00:37";
        BerlinClock berlinClock = new BerlinClock(new HourFormatter());

        BerlinClockTime berlinClockTimeEvenSecond = berlinClock.getBerlinClockTime(hourWithEvenSecond);
        BerlinClockTime berlinClockTimeOddSecond = berlinClock.getBerlinClockTime(hourWithOddSecond);
        BerlinClockTime berlinClockTimeAnotherEvenSecond = berlinClock.getBerlinClockTime(hourWithAnotherEvenSecond);
        BerlinClockTime berlinClockTimeAnotherOddSecond = berlinClock.getBerlinClockTime(hourWithAnotherOddSecond);

        assertThat(berlinClockTimeEvenSecond.getTopLamp().getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeOddSecond.getTopLamp().getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTimeAnotherEvenSecond.getTopLamp().getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeAnotherOddSecond.getTopLamp().getState(), is(Lamp.State.OFF));

        assertThat(berlinClockTimeEvenSecond.toStringRepresentation(), is("" +
                "Y\n"
//                +
//                "OOOO\n" +
//                "OOOO\n" +
//                "OOOOOOOOOO\n" +
//                "OOOO"
        ));
        assertThat(berlinClockTimeOddSecond.toStringRepresentation(), is("" +
                "O\n"
//                +
//                "OOOO\n" +
//                "OOOO\n" +
//                "OOOOOOOOOOO\n" +
//                "OOOO"
        ));
    }
}
