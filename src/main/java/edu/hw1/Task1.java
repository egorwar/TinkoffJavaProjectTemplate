package edu.hw1;

public class Task1 {

    private Task1() {
    }

    /**
     * Converts given timespan to seconds
     *
     * @param timespan a string of "mm:ss" format, where "mm" can be any number of minutes, and "ss" < 60 (seconds)
     * @return given timespan in seconds, -1 for any incorrect input
     */
    @SuppressWarnings("MagicNumber")
    public static long minutesToSeconds(String timespan) {

        String[] splittedTimespan = timespan.split(":");

        if (splittedTimespan.length != 2) {
            return -1;
        }

        long minutes;
        long seconds;

        try {
            minutes = Long.parseLong(splittedTimespan[0]);
            seconds = Long.parseLong(splittedTimespan[1]);
        } catch (NumberFormatException e) {
            return -1;
        }

        if (minutes < 0 || seconds < 0 || seconds > 59) {
            return -1;
        } else {
            return minutes * 60 + seconds;
        }

    }
}
