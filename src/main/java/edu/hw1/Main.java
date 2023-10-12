package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        // Task 0
//        helloWorld();
        // Task 1
//        LOGGER.info(Task1.minutesToSeconds("01:00"));
        // Task 2
//          LOGGER.info(Task2.countDigits(-12));
        // Task 3
//            LOGGER.info(Task3.isNestable(new int[] {1, -2}, new int[] {1, -2}));
        // Task 4
              LOGGER.info(Task4.fixString("оПомигети псаривьтс ртко!и"));
    }

    // Task 0
    public static void helloWorld() {
        LOGGER.info("Привет, мир!");
    }
}
