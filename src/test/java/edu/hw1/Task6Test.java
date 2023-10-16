package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task6Test {
    @Test
    @DisplayName("Проверка на отрицательное число")
    void negativeNum() {
        // given
        int num = -2222;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task6.countK(num),
            "Expected Task6.countK to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Should be a 4-digit number > 1000");
    }

    @Test
    @DisplayName("Проверка на некорректное положительное число")
    void incorrectPositiveNum() {
        // given
        int num = 1000;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task6.countK(num),
            "Expected Task6.countK to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Should be a 4-digit number > 1000");
    }

    @Test
    @DisplayName("Проверка на четырёхзначное число, у которого все цифры одинаковы")
    void allSameDigitsNum() {
        // given
        int num = 9999;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task6.countK(num),
            "Expected Task6.countK to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("All digits cannot be the same");
    }

    @Test
    @DisplayName("Число Капрекара передаётся сразу")
    void zeroSteps() {
        // given
        int num = 6174;

        // when
        int res = Task6.countK(num);

        // then
        assertThat(res).isEqualTo(0);
    }

    @Test
    @DisplayName("Стандартный проход")
    void severalSteps() {
        // given
        int num = 6621;

        // when
        int res = Task6.countK(num);

        // then
        assertThat(res).isEqualTo(5);
    }
}
