package org.kata.berlin.clock;

import com.google.common.base.Splitter;

import java.util.List;

public class HourFormatter {

    private static final String EXPECTED_FORMAT_OF_CLASSIC_HOUR = "^(\\d{2})::(\\d{2}):(\\d{2})$";

    public Time splitTime(String classicHourRepresentation) {

        if (!classicHourRepresentation.matches(EXPECTED_FORMAT_OF_CLASSIC_HOUR)) {
            throw new IllegalArgumentException("Wrong hour format.");
        }

        List<String> hoursAndRest = Splitter.on("::").splitToList(classicHourRepresentation);
        List<String> minutesAndSeconds = Splitter.on(":").splitToList(hoursAndRest.get(1));

        Integer hours = Integer.valueOf(hoursAndRest.get(0));
        Integer minutes = Integer.valueOf(minutesAndSeconds.get(0));
        Integer seconds = Integer.valueOf(minutesAndSeconds.get(1));
        return new Time(hours, minutes, seconds);
    }
}
