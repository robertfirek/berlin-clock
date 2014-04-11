package org.kata.berlin.clock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RowTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldStoreLamps() throws Exception {
        Lamp firstLamp = new Lamp(Lamp.State.OFF);
        Lamp secondLamp = new Lamp(Lamp.State.YELLOW);
        Row row = new Row(firstLamp, secondLamp);

        assertThat(row.getLamp(1), equalTo(firstLamp));
        assertThat(row.getLamp(2), equalTo(secondLamp));
    }


    @Test
    public void shouldReturnNumberOfLamps() throws Exception {
        Lamp firstLamp = new Lamp(Lamp.State.OFF);
        Lamp secondLamp = new Lamp(Lamp.State.YELLOW);
        Lamp thirdLamp = new Lamp(Lamp.State.YELLOW);
        Row row = new Row(firstLamp, secondLamp, thirdLamp);

        assertThat(row.getLamp(1), equalTo(firstLamp));
        assertThat(row.getLamp(2), equalTo(secondLamp));
        assertThat(row.getLamp(3), equalTo(thirdLamp));
        assertThat(row.size(), is(3));
    }

    @Test
    public void mustHaveLamps() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Lamps must be defined."));

        new Row(null);
    }

    @Test
    public void mustReturnOnlyAvailableLamp() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Row contains only 2 lamps."));
        Lamp firstLamp = new Lamp(Lamp.State.OFF);
        Lamp secondLamp = new Lamp(Lamp.State.YELLOW);
        Row row = new Row(firstLamp, secondLamp);

        row.getLamp(3);
    }

    @Test
    public void mustReturnStringRepresentation() throws Exception {
        Lamp firstLamp = new Lamp(Lamp.State.OFF);
        Lamp secondLamp = new Lamp(Lamp.State.YELLOW);
        Row row = new Row(firstLamp, secondLamp);
        Row reversedRow = new Row(secondLamp, firstLamp);

        assertThat(row.toStringRepresentation(), is("OY"));
        assertThat(reversedRow.toStringRepresentation(), is("YO"));
    }
}
