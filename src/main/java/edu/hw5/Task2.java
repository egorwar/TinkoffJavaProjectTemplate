package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class Task2 {

    private Task2() {
    }

    public static List<LocalDate> findAllFridays13byYear(int year) {

        if (year < 0) {
            throw new IllegalArgumentException("A year should be positive");
        }

        List<LocalDate> fridays13 = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {
            LocalDate date = LocalDate.of(year, month, 13);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays13.add(date);
            }
        }

        return fridays13;
    }

    public static LocalDate findNextFriday13(LocalDate date) {
        LocalDate nextFriday13 = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        while (nextFriday13.getDayOfMonth() != 13) {
            nextFriday13 = nextFriday13.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }

        return nextFriday13;
    }

}
