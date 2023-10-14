package edu.hw1;

public class Task5 {
    /**
     * Checks whether a number is a palindrome or any of its "descendants" of at least two digits is a palindrome.
     * <p>
     * Here, a "descendant" of a given number is a number formed by summing up each consecutive pair of digits of a
     * given number.
     *
     * @param num a non-negative integer, for which the described condition should be checked
     * @return whether the described condition is fulfilled for {@code num}
     * @throws IllegalArgumentException if {@code num} is negative
     */
    public static boolean isPalindromeDescendant(int num) {

        if (num < 0) {
            throw new IllegalArgumentException("A number should be positive");
        }

        if (num <= 9) {
            return true;
        }

        while (num != -1) {
            if (isPalindrome(num) && num > 9) {
                return true;
            } else {
                num = getDescendant(num);
            }
        }

        return false;
    }

    private static int getDescendant(int num) {

        int descendant = 0;
        int multiplier = 1;

        while (num != 0) {
            int pairSum = num % 10;

            num /= 10;
            if (num == 0) {
                // due to definition ambiguity, let's suppose that numbers with odd lengths cannot have descendants
                return -1;
            }

            pairSum += num % 10;
            descendant += pairSum * multiplier;

            num /= 10;
            multiplier *= 10;
            if (pairSum > 9) {
                multiplier *= 10;
            }
        }

        return descendant;
    }

    private static boolean isPalindrome(int num) {

        String strRepr = String.valueOf(num);
        return strRepr.contentEquals(new StringBuilder(strRepr).reverse());
    }
}
