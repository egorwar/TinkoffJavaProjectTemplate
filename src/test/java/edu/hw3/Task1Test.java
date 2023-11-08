package edu.hw3;

import edu.hw3.Task1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Проверка на примере 1")
    void example1() {
        // given
        String string = "Hello world!";

        // when
        String inverse = Task1.atbash(string);

        // then
        assertThat(inverse).isEqualTo("Svool dliow!");
    }

    @Test
    @DisplayName("Проверка на примере 2")
    void example2() {
        // given
        String string =
            "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        // when
        String inverse = Task1.atbash(string);

        // then
        assertThat(inverse).isEqualTo(
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }

    @Test
    @DisplayName("Проверка на пустую строку")
    void emptyStringTest() {
        // given
        String string = "";

        // when
        String inverse = Task1.atbash(string);

        // then
        assertThat(inverse).isEqualTo(string);
    }

    @Test
    @DisplayName("Проверка на неизменяемые символы")
    void nonLatinStringTest() {
        // given
        String string = "12345 !№;% Привет МИР!";

        // when
        String inverse = Task1.atbash(string);

        // then
        assertThat(inverse).isEqualTo(string);
    }
}

