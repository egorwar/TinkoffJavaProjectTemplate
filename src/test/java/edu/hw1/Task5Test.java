package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task5Test {
    @Test
    @DisplayName("Проверка на отрицательное число")
    void negativeNum() {
        // given
        int num = -22;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task5.isPalindromeDescendant(num),
            "Expected Task5.isPalindromeDescendant to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("A number should be positive");
    }

    @Test
    @DisplayName("Однозначное число - всегда палиндром")
    void oneDigitNum() {
        // given
        int num = 0;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Не однозначное число-палиндром")
    void manyDigitPalindrome() {
        // given
        int num = 123454321;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Не однозначное число не-палиндром с палиндромом-наследником")
    void manyDigitNonPalindromeWithPalindromeDescendant() {
        // given
        int num = 11211230;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Не однозначное число не-палиндром с одно-символьным наследником")
    void manyDigitNonPalindromeWithSingleDigitDescendant() {
        // given
        int num = 1121;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("Не однозначное число не-палиндром с наследником нечетной длины")
    void manyDigitNonPalindromeWithOddLengthDescendant() {
        // given
        int num = 9945;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isFalse();
    }
}
