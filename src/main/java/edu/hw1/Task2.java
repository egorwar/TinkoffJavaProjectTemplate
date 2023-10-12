package edu.hw1;

public class Task2 {
    public static int countDigits(long num) {
        if (num == 0) {
            return 1;
        } else if (num < 0) {
            num *= -1;
        }
        return (int) (Math.log10(num) + 1);
    }
}
