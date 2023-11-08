package edu.hw3;

import edu.hw3.task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {
    @Test
    @DisplayName("Проверка на примере 1")
    void example1() {
        // given
        int arabic = 19;
        String trueRoman = "XIX";

        // when
        String gotRoman = Task4.arabicToRoman(arabic);

        // then
        assertThat(gotRoman).isEqualTo(trueRoman);
    }

    @Test
    @DisplayName("Проверка на примере 2")
    void example2() {
        // given
        int arabic = 1999;
        String trueRoman = "MCMXCIX";

        // when
        String gotRoman = Task4.arabicToRoman(arabic);

        // then
        assertThat(gotRoman).isEqualTo(trueRoman);

    }

    @Test
    @DisplayName("Проверка на некорректное число 1")
    void zeroTest() {
        // given
        int arabic = 0;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task4.arabicToRoman(arabic),
            "Expected Task4.arabicToRoman to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "a number should be in range (0,4000]");
    }

    @Test
    @DisplayName("Проверка на некорректное число 2")
    void upperBoundTest() {
        // given
        int arabic = 4001;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task4.arabicToRoman(arabic),
            "Expected Task4.arabicToRoman to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "a number should be in range (0,4000]");
    }
}
