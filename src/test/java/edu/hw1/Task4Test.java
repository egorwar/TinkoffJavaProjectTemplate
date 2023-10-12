package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка на пустую строку")
    void emptyString() {
        // given
        String s = "";

        // when
        String res = Task4.fixString(s);

        // then
        assertThat(res).isEqualTo("");
    }

    @Test
    @DisplayName("Проверка на единичную строку")
    void oneElementString() {
        // given
        String s = "x";

        // when
        String res = Task4.fixString(s);

        // then
        assertThat(res).isEqualTo("x");
    }

    @Test
    @DisplayName("Проверка на строку нечетной длины")
    void oddLengthString() {
        // given
        String s = "lol";

        // when
        String res = Task4.fixString(s);

        // then
        assertThat(res).isEqualTo("oll");
    }

    @Test
    @DisplayName("Проверка на строку четной длины")
    void evenLengthString() {
        // given
        String s = "aJavi  soclo";

        // when
        String res = Task4.fixString(s);

        // then
        assertThat(res).isEqualTo("Java is cool");
    }
}
