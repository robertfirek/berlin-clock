package org.kata.berlin.clock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RowsTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldStoreTwoRows() throws Exception {
        Row firstRow = new Row(new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.OFF));
        Row secondRow = new Row(new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW));
        Rows rows = new Rows(firstRow, secondRow);

        assertThat(rows.getFirstRow(), equalTo(firstRow));
        assertThat(rows.getSecondRow(), equalTo(secondRow));
    }

    @Test
    public void mustHaveFirstRow() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("First row must be defined."));
        Row secondRow = new Row();
        new Rows(null, secondRow);
    }


    @Test
    public void mustHaveSecondRow() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is("Second row must be defined."));

        Row firstRow = new Row();

        new Rows(firstRow, null);
    }

    @Test
    public void mustReturnStringRepresentation() throws Exception {
        Row firstRow = new Row(new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.OFF));
        Row secondRow = new Row(new Lamp(Lamp.State.YELLOW), new Lamp(Lamp.State.YELLOW));
        Rows rows = new Rows(firstRow, secondRow);

        assertThat(rows.toStringRepresentation(), is("YO\nYY"));
    }
}
