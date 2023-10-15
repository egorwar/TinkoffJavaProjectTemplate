package edu.hw1;

public class Task4 {

    private Task4() {
    }

    /**
     * Fixes the string, where each consecutive pair of characters has been swapped.
     *
     * @param s a permuted string to fix
     * @return a fixed string {@code s}
     */
    public static String fixString(String s) {
        char[] fixed = s.toCharArray();

        int oddIrrelevantLength = fixed.length;
        if (fixed.length % 2 != 0) {
            oddIrrelevantLength--;
        }

        for (int i = 0; i < oddIrrelevantLength; i += 2) {
            char buf = fixed[i];
            fixed[i] = fixed[i + 1];
            fixed[i + 1] = buf;
        }

        return new String(fixed);
    }
}
