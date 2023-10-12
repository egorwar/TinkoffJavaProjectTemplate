package edu.hw1;

import java.util.Arrays;

public class Task4 {
    public static String fixString(String s) {
        char[] fixed = s.toCharArray();
        int n = fixed.length % 2 == 0 ? fixed.length : fixed.length - 1;
        for (int i = 0; i < n; i += 2) {
            char buf = fixed[i];
            fixed[i] = fixed[i + 1];
            fixed[i + 1] = buf;
        }

        return new String(fixed);
    }
}
