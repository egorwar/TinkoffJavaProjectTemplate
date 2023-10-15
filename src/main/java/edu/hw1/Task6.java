package edu.hw1;

import java.util.Arrays;

public class Task6 {

    static final int KAPRENKAR_CONST = 6174;

    private Task6() {
    }

    /**
     * Counts the number of steps needed to reach the Kaprekar number from given number
     *
     * @param num a 4-digit number > 1000, where all digits cannot be the same
     * @return the number of steps to reach the Kaprekar number from {@code num}
     * @throws IllegalArgumentException if {@code num} is not a 4-digit number > 1000 or all digits are the same
     */
    public static int countK(int num) {

        if (num == KAPRENKAR_CONST) {
            return 0;
        } else {
            return 1 + countK(getKaprekarNum(num));
        }

    }

    @SuppressWarnings("MagicNumber")
    private static int getKaprekarNum(int num) {

        if (num <= 1000 || num > 9999) {
            throw new IllegalArgumentException("Should be a 4-digit number > 1000");
        }

        int[] digitArray = new int[] {num % 10, num / 10 % 10, num / 100 % 10, num / 1000};

        if (digitArray[0] == digitArray[1]
            && digitArray[1] == digitArray[2]
            && digitArray[2] == digitArray[3]) {

            throw new IllegalArgumentException("All digits cannot be the same");
        }

        Arrays.sort(digitArray);

        int ascSorted = 0;
        int descSorted = 0;

        for (int i = 0; i < 4; i++) {
            int multiplier = (int) Math.pow(10, i);
            descSorted += digitArray[i] * multiplier;
            ascSorted += digitArray[4 - i - 1] * multiplier;
        }

        return descSorted - ascSorted;
    }
}
