package org.kata.berlin.clock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TimeTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldBeAbleStoreBasicTimeInformation() throws Exception {
        int hours = 23;
        int minutes = 59;
        int seconds = 59;

        Time time = new Time(hours, minutes, seconds);

        assertThat(time.getHours(), is(hours));
        assertThat(time.getMinutes(), is(minutes));
        assertThat(time.getSeconds(), is(seconds));
    }

    @Test
    public void shouldStoreOnlyPositiveHours() throws Exception {

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Hour must be between 0 and 24."));
        int hours = -1;
        int minutes = 39;
        int seconds = 59;

        new Time(hours, minutes, seconds);

    }

    @Test
    public void shouldStoreOnlyHoursLowerOrEqualTo24() throws Exception {

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Hour must be between 0 and 24."));
        int hours = 25;
        int minutes = 39;
        int seconds = 59;

        new Time(hours, minutes, seconds);

    }

    @Test
    public void shouldStoreOnlyPositiveMinutes() throws Exception {

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Minutes must be between 0 and 59."));
        int hours = 1;
        int minutes = -39;
        int seconds = 59;

        new Time(hours, minutes, seconds);

    }



    @Test
    public void shouldStoreOnlyMinutesLowerOrEqualTo59() throws Exception {

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Minutes must be between 0 and 59."));
        int hours = 1;
        int minutes = 60;
        int seconds = 59;

        new Time(hours, minutes, seconds);

    }

    @Test
    public void shouldStoreOnlyPositiveSeconds() throws Exception {

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Seconds must be between 0 and 59."));
        int hours = 14;
        int minutes = 39;
        int seconds = -59;

        new Time(hours, minutes, seconds);

    }


    @Test
    public void shouldStoreOnlySecondsLowerOrEqualTo59() throws Exception {

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Seconds must be between 0 and 59."));
        int hours = 14;
        int minutes = 59;
        int seconds = 60;

        new Time(hours, minutes, seconds);

    }

    @Test
    public void shouldStoreMidnightTimeIn24Format() throws Exception {

        int hours = 24;
        int minutes = 0;
        int seconds = 0;

        Time time = new Time(hours, minutes, seconds);

        assertThat(time.getHours(), is(hours));
        assertThat(time.getMinutes(), is(minutes));
        assertThat(time.getSeconds(), is(seconds));
    }

    @Test
    public void shouldStoreOnlyTimeBeforeMidnight() throws Exception {

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Time cannot store time after 24::00:00."));
        int hours = 24;
        int minutes = 39;
        int seconds = 59;

        new Time(hours, minutes, seconds);

    }
}
