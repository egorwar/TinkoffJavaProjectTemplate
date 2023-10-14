package edu.hw1;

public class Task2 {
    /**
     * Counts the number of digits in the whole number
     *
     * @param num a whole number
     * @return the number of digits in the given number
     */
    public static int countDigits(long num) {
        if (num == 0) {
            return 1;
        } else if (num < 0) {
            num *= -1;
        }
        return (int) (Math.log10(num) + 1);
    }
}
