package edu.hw1;

public class Task2 {

    private Task2() {
    }

    /**
     * Counts the number of digits in the whole number
     *
     * @param num a whole number
     * @return the number of digits in the given number
     */
    @SuppressWarnings({"ParameterAssignment", "MagicNumber"})
    public static int countDigits(long num) {

        if (num == 0) {
            return 1;
        }

        int digits = 0;

        while (num != 0) {

            num /= 10;
            digits++;

        }

        return digits;
    }
}
