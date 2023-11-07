package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    @Test
    @DisplayName("Проверка на примере 1")
    void example1() {
        // given
        String string = "()()(())()";

        // when
        ArrayList<String> clusterized = Task2.clusterize(string);

        // then
        assertThat(clusterized).isEqualTo(new ArrayList<String>(List.of("()", "()", "(())", "()")));
    }

    @Test
    @DisplayName("Проверка на примере 2")
    void example2() {
        // given
        String string = "((()))(())()()(()())";

        // when
        ArrayList<String> clusterized = Task2.clusterize(string);

        // then
        assertThat(clusterized).isEqualTo(new ArrayList<String>(List.of("((()))", "(())", "()", "()", "(()())")));
    }

    @Test
    @DisplayName("Проверка на пустую строку")
    void emptyStringTest() {
        // given
        String string = "";

        // when
        ArrayList<String> clusterized = Task2.clusterize(string);

        // then
        assertThat(clusterized).isEqualTo(new ArrayList<String>(List.of("")));
    }

    @Test
    @DisplayName("Проверка на строку с несовпадающим числом скобок")
    void unevenBracesTest() {
        // given
        String string = "()(())()()Hello, world!))))";

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task2.clusterize(string),
            "Expected Task2.clusterize to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "The number of opening and closing brackets should be equal in the argument string");
    }
}

