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
        Row hourRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED));
        Rows minuteRows = getMinuteRows();

        BerlinClockTime berlinClockTimeSecondsOff = new BerlinClockTime(new Lamp(Lamp.State.OFF), new Rows(hourRow, hourRow), minuteRows);
        BerlinClockTime berlinClockTimeSecondsOn = new BerlinClockTime(new Lamp(Lamp.State.YELLOW), new Rows(hourRow, hourRow), minuteRows);

        assertThat(berlinClockTimeSecondsOff.getTopLamp().getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTimeSecondsOn.getTopLamp().getState(), is(Lamp.State.YELLOW));
        assertThat(berlinClockTimeSecondsOff.getTopLamp().toStringRepresentation(), is("O"));
        assertThat(berlinClockTimeSecondsOn.getTopLamp().toStringRepresentation(), is("Y"));
    }


    @Test
    public void shouldHaveRowsToRepresentHours() throws Exception {
        Rows hourRows = getHourRows();
        Rows minuteRows = getMinuteRows();
        BerlinClockTime berlinClockTime = new BerlinClockTime(new Lamp(Lamp.State.OFF), hourRows, minuteRows);

        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(1).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(2).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(3).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(4).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(1).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(2).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(3).getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(4).getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTime.toStringRepresentation(), is("O\nRRRR\nRROO\nYYRYYRYYRYY\nYYOO"));
    }

    @Test
    public void shouldHaveRowsToRepresentMinutes() throws Exception {
        Rows hourRows = getHourRows();
        Rows minuteRows = getMinuteRows();
        BerlinClockTime berlinClockTime = new BerlinClockTime(new Lamp(Lamp.State.OFF), hourRows, minuteRows);

        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(1).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(2).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(3).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getFirstRow().getLamp(4).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(1).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(2).getState(), is(Lamp.State.RED));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(3).getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTime.getHourRows().getSecondRow().getLamp(4).getState(), is(Lamp.State.OFF));
        assertThat(berlinClockTime.toStringRepresentation(), is("O\nRRRR\nRROO\nYYRYYRYYRYY\nYYOO"));

    }

    @Test
    public void shouldHaveHourRowsWhereFirstRowCannotHaveMoreThanFourLamps() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("First hour row must have 4 lamps."));
        Row hourFirstRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));
        Row hourSecondRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));
        Rows hoursRows = new Rows(hourFirstRow, hourSecondRow);
        Rows minuteRows = getMinuteRows();

        new BerlinClockTime(new Lamp(Lamp.State.OFF), hoursRows, minuteRows);
    }

    @Test
    public void shouldHaveHourRowsWhereFirstRowCannotHaveLessThanFourLamps() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("First hour row must have 4 lamps."));
        Row hourFirstRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.RED));
        Row hourSecondRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));
        Rows hoursRows = new Rows(hourFirstRow, hourSecondRow);
        Rows minuteRows = getMinuteRows();

        new BerlinClockTime(new Lamp(Lamp.State.OFF), hoursRows, minuteRows);
    }


    @Test
    public void shouldHaveHourRowsWhereSecondRowCannotHaveMoreThanFourLamps() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Second hour row must have 4 lamps."));
        Row hourFirstRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.OFF));
        Row hourSecondRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));
        Rows hourRows = new Rows(hourFirstRow, hourSecondRow);
        Rows minuteRows = getMinuteRows();

        new BerlinClockTime(new Lamp(Lamp.State.OFF), hourRows, minuteRows);
    }

    @Test
    public void shouldHaveHourRowsWhereSecondRowCannotHaveLessThanFourLamps() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Second hour row must have 4 lamps."));
        Row hourFirstRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.OFF));
        Row hourSecondRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));
        Rows hourRows = new Rows(hourFirstRow, hourSecondRow);
        Rows minuteRows = getMinuteRows();

        new BerlinClockTime(new Lamp(Lamp.State.OFF), hourRows, minuteRows);
    }

    @Test
    public void shouldHaveMinuteRowsWhereFirstRowCannotHaveMoreThanElevenLamps() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("First minute row must have 11 lamps."));
        Rows hourRows = getHourRows();
        Row minuteFirstRow = new Row(
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED));
        Row minuteSecondRow = new Row(
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW),
                new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));
        Rows minuteRows = new Rows(minuteFirstRow, minuteSecondRow);

        new BerlinClockTime(new Lamp(Lamp.State.OFF), hourRows, minuteRows);
    }

    @Test
    public void shouldHaveMinuteRowsWhereFirstRowCannotHaveLessThanElevenLamps() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("First minute row must have 11 lamps."));
        Rows hourRows = getHourRows();
        Row minuteFirstRow = new Row(
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW));
        Row minuteSecondRow = new Row(
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW),
                new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));
        Rows minuteRows = new Rows(minuteFirstRow, minuteSecondRow);

        new BerlinClockTime(new Lamp(Lamp.State.OFF), hourRows, minuteRows);
    }


    @Test
    public void shouldHaveMinuteRowsWhereSecondRowCannotHaveMoreThanFourLamps() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Second minute row must have 4 lamps."));
        Rows hourRows = getHourRows();
        Row minuteFirstRow = new Row(
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW));
        Row minuteSecondRow = new Row(
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW),
                new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));
        Rows minuteRows = new Rows(minuteFirstRow, minuteSecondRow);

        new BerlinClockTime(new Lamp(Lamp.State.OFF), hourRows, minuteRows);
    }

    @Test
    public void shouldHaveMinuteRowsWhereSecondRowCannotHaveLessThanFourLamps() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Second minute row must have 4 lamps."));
        Rows hourRows = getHourRows();
        Row minuteFirstRow = new Row(
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW));
        Row minuteSecondRow = new Row(
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW),
                new Lamp(Lamp.State.OFF));
        Rows minuteRows = new Rows(minuteFirstRow, minuteSecondRow);

        new BerlinClockTime(new Lamp(Lamp.State.OFF), hourRows, minuteRows);
    }

    @Test
    public void mustHaveLampForSeconds() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Lamp for seconds must be defined."));
        Rows minuteRows = getMinuteRows();

        new BerlinClockTime(null, null, minuteRows);
    }

    @Test
    public void mustHaveHourRows() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Hour rows must be defined."));
        Rows minuteRows = getMinuteRows();

        new BerlinClockTime(new Lamp(Lamp.State.OFF), null, minuteRows);
    }

    @Test
    public void mustHaveMinuteRows() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Minute rows must be defined."));
        Rows hourRows = getHourRows();

        new BerlinClockTime(new Lamp(Lamp.State.OFF), hourRows, null);
    }

    private Rows getHourRows() {
        Row hourFirstRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED));
        Row hourSecondRow = new Row(
                new Lamp(Lamp.State.RED), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));
        return new Rows(hourFirstRow, hourSecondRow);
    }


    private Rows getMinuteRows() {
        Row minuteFirstRow = new Row(
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.RED),
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW));
        Row minuteSecondRow = new Row(
                new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW),
                new Lamp(Lamp.State.OFF), new Lamp(Lamp.State.OFF));
        return new Rows(minuteFirstRow, minuteSecondRow);
    }
}
