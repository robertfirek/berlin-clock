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
                "RRRR\n" +
                "OOOOOOOOOOO\n" +
                "OOOO"
        ));
        assertThat(berlinClockTimeRandom.toStringRepresentation(), is("" +
                "O\n" +
                "RROO\n" +
                "RRRO\n" +
                "YYRYYROOOOO\n" +
                "YYOO"
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
                "OOOO\n" +
                "OOOOOOOOOOO\n" +
                "OOOO"
        ));
        assertThat(berlinClockTimeOddSecond.toStringRepresentation(), is("" +
                "O\n" +
                "OOOO\n" +
                "OOOO\n" +
                "OOOOOOOOOOO\n" +
                "OOOO"
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
                "OOOO\n" +
                "OOOOOOOOOOO\n" +
                "OOOO"
        ));
        assertThat(berlinClockTimeBeforeFive.toStringRepresentation(), is("" +
                "Y\n" +
                "OOOO\n" +
                "RRRO\n" +
                "OOOOOOOOOOO\n" +
                "OOOO"
        ));
        assertThat(berlinClockTimeAfterFive.toStringRepresentation(), is("" +
                "Y\n" +
                "ROOO\n" +
                "ROOO\n" +
                "OOOOOOOOOOO\n" +
                "OOOO"
        ));
        assertThat(berlinClockTimeAfterTen.toStringRepresentation(), is("" +
                "Y\n" +
                "RROO\n" +
                "RRRR\n" +
                "OOOOOOOOOOO\n" +
                "OOOO"
        ));
        assertThat(berlinClockTimeAfterFifteen.toStringRepresentation(), is("" +
                "Y\n" +
                "RRRO\n" +
                "RRRR\n" +
                "OOOOOOOOOOO\n" +
                "OOOO"
        ));
        assertThat(berlinClockTimeAfterTwenty.toStringRepresentation(), is("" +
                "Y\n" +
                "RRRR\n" +
                "RROO\n" +
                "OOOOOOOOOOO\n" +
                "OOOO"
        ));

    }


    @Test
    public void shouldStoreMinutesInTwoRows() throws Exception {
        String someHourMinuteQuarterPast = "15::17:00";
        String someHourMinuteHalfPast = "20::40:00";
        String someHourMinuteQuarterTo = "23::47:00";
        String someHourMinuteFiftyNine = "00::59:00";
        BerlinClock berlinClock = new BerlinClock(new HourFormatter());

        BerlinClockTime berlinClockTimeMinuteQuarterPast = berlinClock.getBerlinClockTime(someHourMinuteQuarterPast);
        BerlinClockTime berlinClockTimeMinuteHalfPast = berlinClock.getBerlinClockTime(someHourMinuteHalfPast);
        BerlinClockTime berlinClockTimeMinuteQuarterTo = berlinClock.getBerlinClockTime(someHourMinuteQuarterTo);
        BerlinClockTime berlinClockTimeMinuteFiftyNine = berlinClock.getBerlinClockTime(someHourMinuteFiftyNine);

        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getFirstRow().getLamp(1).getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getFirstRow().getLamp(2).getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getFirstRow().getLamp(3).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getFirstRow().getLamp(4).getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getFirstRow().getLamp(5).getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getFirstRow().getLamp(6).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getFirstRow().getLamp(7).getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getFirstRow().getLamp(8).getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getFirstRow().getLamp(9).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getFirstRow().getLamp(10).getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getFirstRow().getLamp(11).getState(), is(Lamp.State.YELLOW));

        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getSecondRow().getLamp(1).getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getSecondRow().getLamp(2).getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getSecondRow().getLamp(3).getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeMinuteFiftyNine.getMinuteRows().getSecondRow().getLamp(4).getState(), is(Lamp.State.YELLOW));


        assertThat(berlinClockTimeMinuteQuarterPast.toStringRepresentation(), is("" +
                "Y\n" +
                "RRRO\n" +
                "OOOO\n" +
                "YYROOOOOOOO\n" +
                "YYOO"
        ));
        assertThat(berlinClockTimeMinuteHalfPast.toStringRepresentation(), is("" +
                "Y\n" +
                "RRRR\n" +
                "OOOO\n" +
                "YYRYYRYYOOO\n" +
                "OOOO"
        ));
        assertThat(berlinClockTimeMinuteQuarterTo.toStringRepresentation(), is("" +
                "Y\n" +
                "RRRR\n" +
                "RRRO\n" +
                "YYRYYRYYROO\n" +
                "YYOO"
        ));
        assertThat(berlinClockTimeMinuteFiftyNine.toStringRepresentation(), is("" +
                "Y\n" +
                "OOOO\n" +
                "OOOO\n" +
                "YYRYYRYYRYY\n" +
                "YYYY"
        ));

    }
}
