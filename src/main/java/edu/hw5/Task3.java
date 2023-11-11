package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Task3 {

    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        try {

            return Optional.of(LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-M-d")));

        } catch (Exception e) {

            try {

                return Optional.of(LocalDate.parse(string, DateTimeFormatter.ofPattern("d/M/[uuuu][uu]")));

            } catch (Exception ex) {

                LocalDate parsedDate = parseRelativeDate(string);
                if (parsedDate != null) {
                    return Optional.of(parsedDate);
                }

            }

        }

        return Optional.empty();
    }

    @SuppressWarnings({"ReturnCount", "MagicNumber"})
    private static LocalDate parseRelativeDate(String string) {

        LocalDate today = LocalDate.now();

        if (string.equals("tomorrow")) {

            return today.plusDays(1);

        } else if (string.equals("today")) {

            return today;

        } else if (string.equals("yesterday")) {

            return today.minusDays(1);

        } else if (string.endsWith(" days ago")) {

            try {
                var splitted = string.split(" ");
                if (splitted.length != 3) {
                    return null;
                }
                int days = Integer.parseInt(string.split(" ")[0]);
                return today.minusDays(days);

            } catch (Exception e) {
                return null;
            }

        }

        return null;
    }
}
