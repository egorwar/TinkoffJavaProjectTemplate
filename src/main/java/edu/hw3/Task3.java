package edu.hw3;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task3 {

    private Task3() {
    }

    public static <T> Map<T, Long> freqDict(T[] list) {

        if (list.length == 0) {
            throw new IllegalArgumentException("Array argument shouldn't be empty");
        }

        return Arrays.stream(list).collect(
            Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
            )
        );
    }

}
