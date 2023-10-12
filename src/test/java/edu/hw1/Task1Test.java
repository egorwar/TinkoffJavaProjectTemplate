package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Проверка на неотформатированную строку: без двоеточия")
    void nonFormatInput1() {
        // given
        String time = "12345";

        // when
        long inSeconds = Task1.minutesToSeconds(time);

        // then
        assertThat(inSeconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка на неотформатированную строку: несколько двоеточий")
    void nonFormatInput2() {
        // given
        String time = "1::45";

        // when
        long inSeconds = Task1.minutesToSeconds(time);

        // then
        assertThat(inSeconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка на неотформатированную строку")
    void nonFormatInput3() {
        // given
        String time = "12:";

        // when
        long inSeconds = Task1.minutesToSeconds(time);

        // then
        assertThat(inSeconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка на неотформатированную строку")
    void nonFormatInput4() {
        // given
        String time = ":59";

        // when
        long inSeconds = Task1.minutesToSeconds(time);

        // then
        assertThat(inSeconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка на символьный ввод")
    void symbolInput() {
        // given
        String time = "hello:world";

        // when
        long inSeconds = Task1.minutesToSeconds(time);

        // then
        assertThat(inSeconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка на отрицательные числа")
    void negativeNumbersInput() {
        // given
        String time = "-13:-05";

        // when
        long inSeconds = Task1.minutesToSeconds(time);

        // then
        assertThat(inSeconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка на > 59 секунд")
    void incorrectSecondsInput() {
        // given
        String time = "12:60";

        // when
        long inSeconds = Task1.minutesToSeconds(time);

        // then
        assertThat(inSeconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка на работу при корректном времени")
    void correctInput() {
        // given
        String time = "99999:59";

        // when
        long inSeconds = Task1.minutesToSeconds(time);

        // then
        assertThat(inSeconds).isEqualTo(99999 * 60 + 59);
    }
}

