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
                "Y\n" +
                "RRRR\n" +
                "RRRR"
//                "OOOOOOOOOOO\n" +
//                "OOOO"
        ));
        assertThat(berlinClockTimeRandom.toStringRepresentation(), is("" +
                "O\n" +
                "RROO\n" +
                "RRRO"
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
                "Y\n" +
                "OOOO\n" +
                "OOOO"
//                "OOOOOOOOOO\n" +
//                "OOOO"
        ));
        assertThat(berlinClockTimeOddSecond.toStringRepresentation(), is("" +
                "O\n" +
                "OOOO\n" +
                "OOOO"
//                "OOOOOOOOOOO\n" +
//                "OOOO"
        ));
    }

    @Test
    public void shouldStoreHoursInTwoRows() throws Exception {
        String midnight = "00::00:00";
        String someHourBeforeFive = "03::00:00";
        String someHourBeforeAfterFive = "06::00:00";
        String someHourBeforeAfterTen = "14::00:00";
        String someHourBeforeAfterFifteen = "19::00:00";
        String someHourBeforeAfterTwenty = "22::00:00";
        BerlinClock berlinClock = new BerlinClock(new HourFormatter());

        BerlinClockTime berlinClockTimeMidnight = berlinClock.getBerlinClockTime(midnight);
        BerlinClockTime berlinClockTimeBeforeFive = berlinClock.getBerlinClockTime(someHourBeforeFive);
        BerlinClockTime berlinClockTimeAfterFive = berlinClock.getBerlinClockTime(someHourBeforeAfterFive);
        BerlinClockTime berlinClockTimeAfterTen = berlinClock.getBerlinClockTime(someHourBeforeAfterTen);
        BerlinClockTime berlinClockTimeAfterFifteen = berlinClock.getBerlinClockTime(someHourBeforeAfterFifteen);
        BerlinClockTime berlinClockTimeAfterTwenty = berlinClock.getBerlinClockTime(someHourBeforeAfterTwenty);

        assertThat(berlinClockTimeBeforeFive.getHourRows().getFirstRow().getLamp(1).getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTimeBeforeFive.getHourRows().getFirstRow().getLamp(2).getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTimeBeforeFive.getHourRows().getFirstRow().getLamp(3).getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTimeBeforeFive.getHourRows().getFirstRow().getLamp(4).getState(), is(Lamp.State.OFF));

        assertThat(berlinClockTimeBeforeFive.getHourRows().getSecondRow().getLamp(1).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTimeBeforeFive.getHourRows().getSecondRow().getLamp(2).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTimeBeforeFive.getHourRows().getSecondRow().getLamp(3).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTimeBeforeFive.getHourRows().getSecondRow().getLamp(4).getState(), is(Lamp.State.OFF));

        assertThat(berlinClockTimeAfterTwenty.getHourRows().getFirstRow().getLamp(1).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTimeAfterTwenty.getHourRows().getFirstRow().getLamp(2).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTimeAfterTwenty.getHourRows().getFirstRow().getLamp(3).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTimeAfterTwenty.getHourRows().getFirstRow().getLamp(4).getState(), is(Lamp.State.RED));

        assertThat(berlinClockTimeAfterTwenty.getHourRows().getSecondRow().getLamp(1).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTimeAfterTwenty.getHourRows().getSecondRow().getLamp(2).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTimeAfterTwenty.getHourRows().getSecondRow().getLamp(3).getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTimeAfterTwenty.getHourRows().getSecondRow().getLamp(4).getState(), is(Lamp.State.OFF));

        assertThat(berlinClockTimeMidnight.toStringRepresentation(), is("" +
                "Y\n" +
                "OOOO\n" +
                "OOOO"
//                "OOOOOOOOOO\n" +
//                "OOOO"
        ));
        assertThat(berlinClockTimeBeforeFive.toStringRepresentation(), is("" +
                "Y\n" +
                "OOOO\n" +
                "RRRO"
//                "OOOOOOOOOO\n" +
//                "OOOO"
        ));
        assertThat(berlinClockTimeAfterFive.toStringRepresentation(), is("" +
                "Y\n" +
                "ROOO\n" +
                "ROOO"
//                "OOOOOOOOOO\n" +
//                "OOOO"
        ));
        assertThat(berlinClockTimeAfterTen.toStringRepresentation(), is("" +
                "Y\n" +
                "RROO\n" +
                "RRRR"
//                "OOOOOOOOOO\n" +
//                "OOOO"
        ));
        assertThat(berlinClockTimeAfterFifteen.toStringRepresentation(), is("" +
                "Y\n" +
                "RRRO\n" +
                "RRRR"
//                "OOOOOOOOOO\n" +
//                "OOOO"
        ));
        assertThat(berlinClockTimeAfterTwenty.toStringRepresentation(), is("" +
                "Y\n" +
                "RRRR\n" +
                "RROO"
//                "OOOOOOOOOO\n" +
//                "OOOO"
        ));

    }
}
