package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task1 {

    private static final String NULL_TIME = "0ч 0м";

    private Task1() {
    }

    public static String calculateAverageDateDifference(List<String> datePairs) {

        if (datePairs.isEmpty()) {
            return NULL_TIME;
        }

        Duration totalDuration = Duration.ZERO;
        int pairCount = 0;

        for (String datePair : datePairs) {

            String[] dates = datePair.split(" - ");
            LocalDateTime startDateTime = parseDateTime(dates[0]);
            LocalDateTime endDateTime = parseDateTime(dates[1]);

            if (endDateTime.isBefore(startDateTime)) {
                throw new IllegalArgumentException("Invalid date pair order: " + datePair);
            }

            Duration duration = Duration.between(startDateTime, endDateTime);
            totalDuration = totalDuration.plus(duration);
            pairCount++;

        }

        return durationToString(totalDuration.dividedBy(pairCount));
    }

    private static LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    private static String durationToString(Duration duration) {
        return duration.toHours() + "ч " + duration.toMinutesPart() + "м";
    }

}
