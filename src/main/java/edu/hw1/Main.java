package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        // Task 0
//        printHelloWorld();

        // Task 1
//        LOGGER.info(Task1.minutesToSeconds("01:00"));

        // Task 2
//        LOGGER.info(Task2.countDigits(-12));

        // Task 3
//        LOGGER.info(Task3.isNestable(new int[] {1, -2}, new int[] {1, -2}));

        // Task 4
//        LOGGER.info(Task4.fixString("оПомигети псаривьтс ртко!и"));

        // Task 5
//        LOGGER.info(Task5.isPalindromeDescendant(11211230));

        // Task 6
//        LOGGER.info(Task6.countK(1234));

        // Task 7
        LOGGER.info(Task7.rotateLeft(16, 1));
    }

    // Task 0

    /**
     * Writes the "Привет, мир!" phrase into the console
     */
    public static void printHelloWorld() {
        LOGGER.info("Привет, мир!");
    }
}
