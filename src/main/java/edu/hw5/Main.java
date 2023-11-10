package edu.hw5;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
    public static void main(String[] args) {
        // Task 1
        List<String> datePairs = Arrays.asList(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-03-12, 20:20 - 2022-03-12, 23:30",
            "2022-04-01, 21:30 - 2022-04-02, 01:20",
            "2022-04-01, 21:30 - 2022-04-02, 01:40"
        );

        LOGGER.info(Task1.calculateAverageDateDifference(datePairs));

        // Task 2
        LOGGER.info(Task2.findAllFridays13byYear(2024));
        LOGGER.info(Task2.findNextFriday13(LocalDate.now()));

        // Task 3
        // goto tests

        // Task 4
        LOGGER.info(Task4.validatePass("d dfd |||||df$"));

        // Task 5
        LOGGER.info(Task5.validateRusRegPlate("А123БВ666"));

        // Task 6
        LOGGER.info(Task6.isSubstring("abc", "abaccabbcaaabcccaa"));

        // Task 7
        // goto tests

        // Task 8
        // goto tests
    }
}
