package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task7Test {
    @Test
    @DisplayName("Right: Проверка на неположительное число")
    void rightNonPosNum() {
        // given
        int n = -2222;
        int shift = 5;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task7.rotateRight(n, shift),
            "Expected Task7.rotateRight to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Number to rotate should be positive");
    }

    @Test
    @DisplayName("Left: Проверка на неположительное число")
    void leftNonPosNum() {
        // given
        int n = -2222;
        int shift = 5;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task7.rotateLeft(n, shift),
            "Expected Task7.rotateLeft to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Number to rotate should be positive");
    }

    @Test
    @DisplayName("Right: Проверка на отрицательный сдвиг")
    void rightNegShift() {
        // given
        int n = 1;
        int shift = -5;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task7.rotateRight(n, shift),
            "Expected Task7.rotateRight to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("The number of bits to shift cannot be negative");
    }

    @Test
    @DisplayName("Left: Проверка на отрицательный сдвиг")
    void leftNegShift() {
        // given
        int n = 1;
        int shift = -5;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task7.rotateLeft(n, shift),
            "Expected Task7.rotateLeft to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("The number of bits to shift cannot be negative");
    }

    @Test
    @DisplayName("Right: Сдвиг единицы")
    void rightShiftOfOne() {
        // given
        int n = 1;
        int shift = 10;

        // when
        int res = Task7.rotateRight(n, shift);

        // then
        assertThat(res).isEqualTo(1);
    }

    @Test
    @DisplayName("Left: Сдвиг единицы")
    void leftShiftOfOne() {
        // given
        int n = 1;
        int shift = 10;

        // when
        int res = Task7.rotateLeft(n, shift);

        // then
        assertThat(res).isEqualTo(1);
    }

    @Test
    @DisplayName("Right: Сдвиг на ноль")
    void rightShiftByZero() {
        // given
        int n = 10;
        int shift = 0;

        // when
        int res = Task7.rotateRight(n, shift);

        // then
        assertThat(res).isEqualTo(10);
    }

    @Test
    @DisplayName("Left: Сдвиг на ноль")
    void leftShiftByZero() {
        // given
        int n = 10;
        int shift = 0;

        // when
        int res = Task7.rotateLeft(n, shift);

        // then
        assertThat(res).isEqualTo(10);
    }

    @Test
    @DisplayName("Right: Сдвиг")
    void rightShift() {
        // given
        int n = 35;
        int shift = 2;

        // when
        int res = Task7.rotateRight(n, shift);

        // then
        assertThat(res).isEqualTo(56);
    }

    @Test
    @DisplayName("Left: Сдвиг")
    void leftShift() {
        // given
        int n = 35;
        int shift = 2;

        // when
        int res = Task7.rotateLeft(n, shift);

        // then
        assertThat(res).isEqualTo(14);
    }
}
