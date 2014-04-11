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
        Row hoursRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED));

        BerlinClockTime berlinClockTimeSecondsOff = new BerlinClockTime(new Lamp(Lamp.State.OFF), new Rows(hoursRow, hoursRow));
        BerlinClockTime berlinClockTimeSecondsOn = new BerlinClockTime(new Lamp(Lamp.State.YELLOW), new Rows(hoursRow, hoursRow));

        assertThat(berlinClockTimeSecondsOff.getTopLamp().getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTimeSecondsOn.getTopLamp().getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeSecondsOff.getTopLamp().toStringRepresentation(), is("O"));
        assertThat(berlinClockTimeSecondsOn.getTopLamp().toStringRepresentation(), is("Y"));
    }


    @Test
    public void shouldHaveRowsToRepresentHours() throws Exception {
        Row hoursFirstRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED));
        Row hoursSecondRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));

        Rows hoursRows = new Rows(hoursFirstRow, hoursSecondRow);
        BerlinClockTime berlinClockTime = new BerlinClockTime(new Lamp(Lamp.State.OFF), hoursRows);

        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(1).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(2).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(3).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(4).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(1).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(2).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(3).getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(4).getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTime.toStringRepresentation(), is("O\nRRRR\nRROO"));
    }

    @Test
    public void shouldHaveHourRowsWhereFirstRowCannotHaveMoreThanFourLamps() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("First hour row must have 4 lamps."));
        Row hoursFirstRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.RED));
        Row hoursSecondRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));
        Rows hoursRows = new Rows(hoursFirstRow, hoursSecondRow);

        new BerlinClockTime(new Lamp(Lamp.State.OFF), hoursRows);
    }

    @Test
    public void shouldHaveHourRowsWhereFirstRowCannotHaveLessThanFourLamps() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Second hour row must have 4 lamps."));
        Row hoursFirstRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.OFF));
        Row hoursSecondRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.OFF));
        Rows hoursRows = new Rows(hoursFirstRow, hoursSecondRow);

        new BerlinClockTime(new Lamp(Lamp.State.OFF), hoursRows);
    }

    @Test
    public void mustHaveLampForSeconds() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Lamp for seconds must be defined."));

        new BerlinClockTime(null, null);
    }

    @Test
    public void mustHaveHourRows() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Hour rows must be defined."));

        new BerlinClockTime(new Lamp(Lamp.State.OFF), null);
    }
}
