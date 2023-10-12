package edu.hw1;

public class Task1 {
    public static long minutesToSeconds(String s) {
        try {
            String[] splitted = s.split(":");
            if(splitted.length != 2)
                return -1;

            long minutes = Long.parseLong(splitted[0]);
            long seconds = Long.parseLong(splitted[1]);

            if (minutes < 0 || seconds < 0 || seconds > 59) {
                return -1;
            } else {
                return minutes * 60 + seconds;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
