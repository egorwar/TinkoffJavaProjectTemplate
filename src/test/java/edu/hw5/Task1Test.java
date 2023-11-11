package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    @Test
    @DisplayName("Проверка на пустой список")
    void emptyListTest() {
        // given
        List<String> datePairs = new ArrayList<>();
        String expectedMean = "0ч 0м";

        // when
        String trueMean = Task1.calculateAverageDateDifference(datePairs);

        // then
        assertThat(trueMean).isEqualTo(expectedMean);
    }

    @Test
    @DisplayName("Проверка на обычный список")
    void normalListTest() {
        // given
        List<String> datePairs = Arrays.asList(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-03-12, 20:20 - 2022-03-12, 23:30",
            "2022-04-01, 21:30 - 2022-04-02, 01:20",
            "2022-04-01, 21:30 - 2022-04-02, 01:40"
        );
        String expectedMean = "3ч 40м";

        // when
        String trueMean = Task1.calculateAverageDateDifference(datePairs);

        // then
        assertThat(trueMean).isEqualTo(expectedMean);
    }

    @Test
    @DisplayName("Проверка на неверный ввод")
    void invalidOrderTest() {
        // given
        List<String> datePairs = Arrays.asList(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-03-12, 20:20 - 2022-03-12, 23:30",
            "2022-04-01, 21:30 - 2022-04-02, 01:20",
            "2023-04-01, 21:30 - 2022-04-02, 01:40"
        );

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task1.calculateAverageDateDifference(datePairs)
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "Invalid date pair order: 2023-04-01, 21:30 - 2022-04-02, 01:40");
    }

}
