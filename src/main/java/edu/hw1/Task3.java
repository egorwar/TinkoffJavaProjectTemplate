package edu.hw1;

import java.util.Arrays;

public class Task3 {

    private Task3() {
    }

    /**
     * Checks whether the first given array can be nested by the second.
     * <p>
     * "Nested" means that the <b>[minElement; maxElement]</b> span of the first array
     * lies strictly within the <b>[minElement; maxElement]</b> span of the second array.
     *
     * @param inner an unsorted array of integers to be nested by {@code outer}
     * @param outer an unsorted array of integers to nest {@code inner}
     * @return whether {@code inner} can be nested by {@code outer}
     * @throws IllegalArgumentException if any array is empty or outer array consists of less than 2 elements
     */
    public static boolean isNestable(int[] inner, int[] outer) {
        if (inner.length == 0 || outer.length == 0) {
            throw new IllegalArgumentException("Arrays should not be empty");
        }
        if (outer.length == 1) {
            throw new IllegalArgumentException(
                "Outer array should have at least two entries, as they act as nesting borders");
        }

        int innerMin = Arrays.stream(inner).min().getAsInt();
        int innerMax = Arrays.stream(inner).max().getAsInt();
        int outerMin = Arrays.stream(outer).min().getAsInt();
        int outerMax = Arrays.stream(outer).max().getAsInt();

        return (innerMin > outerMin && innerMax < outerMax);
    }

}
