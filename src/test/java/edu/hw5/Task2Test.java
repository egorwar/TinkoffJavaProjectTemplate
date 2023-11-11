package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {

    @Test
    @DisplayName("Проверка на 1925 год")
    void Year1925test() {
        // given
        var expectedFri13 = Arrays.asList(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );

        // when
        var trueFri13 = Task2.findAllFridays13byYear(1925);

        // then
        assertThat(trueFri13).isEqualTo(expectedFri13);
    }

    @Test
    @DisplayName("Проверка на следующую пятницу 13")
    void nextFri13test() {
        // given
        var date = LocalDate.now();
        var expectedNext = LocalDate.of(2024, 9, 13);

        // when
        var trueNext = Task2.findNextFriday13(date);

        // then
        assertThat(trueNext).isEqualTo(expectedNext);
    }

    @Test
    @DisplayName("Проверка на неверный ввод")
    void negativeYearTest() {
        // given
        var year = -1;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task2.findAllFridays13byYear(year)
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "A year should be positive");
    }

}
