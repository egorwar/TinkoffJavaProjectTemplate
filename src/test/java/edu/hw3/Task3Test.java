package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static java.util.Map.entry;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    @Test
    @DisplayName("Проверка строк на примере 1")
    void stringExample1() {
        // given
        String[] string = new String[] {"код", "код", "код", "bug"};

        // when
        var frequencies = Task3.freqDict(string);

        // then
        assertThat(frequencies).isEqualTo(Map.ofEntries(
            entry("код", 3L),
            entry("bug", 1L)
        ));
    }

    @Test
    @DisplayName("Проверка строк на примере 2")
    void stringExample2() {
        // given
        String[] string = new String[] {"this", "and", "that", "and"};

        // when
        var frequencies = Task3.freqDict(string);

        // then
        assertThat(frequencies).isEqualTo(Map.ofEntries(
            entry("this", 1L),
            entry("and", 2L),
            entry("that", 1L)
        ));
    }

    @Test
    @DisplayName("Проверка чисел на примере 1")
    void integerExample1() {
        // given
        Integer[] integers = new Integer[] {1, 2, 1, 2};

        // when
        var frequencies = Task3.freqDict(integers);

        // then
        assertThat(frequencies).isEqualTo(Map.ofEntries(
            entry(1, 2L),
            entry(2, 2L)
        ));
    }

    @Test
    @DisplayName("Проверка на пустой массив")
    void unevenBracesTest() {
        // given
        String[] string = new String[] {};

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task3.freqDict(string),
            "Expected Task3.freqDict to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "Array argument shouldn't be empty");
    }
}
