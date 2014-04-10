package org.kata.berlin.clock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HourFormatterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldSplitHourInTheRightFormat() throws Exception {
        String hourRepresentation = "23::22:21";
        HourFormatter formatter = new HourFormatter();

        Time time = formatter.splitTime(hourRepresentation);

        assertThat(time.getHours(), is(23));
        assertThat(time.getMinutes(), is(22));
        assertThat(time.getSeconds(), is(21));
    }

    @Test
    public void shouldNotAllowToSplitHourInWrongFormat() throws Exception {

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Wrong hour format."));
        String hourRepresentation = "xxs";
        HourFormatter formatter = new HourFormatter();

        formatter.splitTime(hourRepresentation);
    }
}
