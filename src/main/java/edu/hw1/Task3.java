package edu.hw1;

public class Task3 {
    public static boolean isNestable(int[] inner, int[] outer) {
        if (inner.length == 0 || outer.length == 0) {
            throw new IllegalArgumentException("Arrays should not be empty");
        }
        if (outer.length == 1) {
            throw new IllegalArgumentException("Outer array should have at least two entries");
        }

        int[] innerBorders = findMinMax(inner);
        int[] outerBorders = findMinMax(outer);

        return (innerBorders[0] > outerBorders[0] && innerBorders[1] < outerBorders[1]);
    }

    private static int[] findMinMax(int[] a) {
        int min = a[0];
        int max = a[0];

        for (int el : a) {
            if (el < min) {
                min = el;
            }
            if (el > max) {
                max = el;
            }
        }
        return new int[] {min, max};
    }
}
