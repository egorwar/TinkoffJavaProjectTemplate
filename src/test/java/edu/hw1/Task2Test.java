package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Проверка на положительное число")
    void positiveInput() {
        // given
        long num = 12345;

        // when
        int len = Task2.countDigits(num);

        // then
        assertThat(len).isEqualTo(5);
    }

    @Test
    @DisplayName("Проверка на отрицательное число")
    void negativeInput() {
        // given
        long num = -12345;

        // when
        int len = Task2.countDigits(num);

        // then
        assertThat(len).isEqualTo(5);
    }

    @Test
    @DisplayName("Проверка на ноль")
    void incorrectSecondsInput() {
        // given
        long num = 0;

        // when
        int len = Task2.countDigits(num);

        // then
        assertThat(len).isEqualTo(1);
    }
}
