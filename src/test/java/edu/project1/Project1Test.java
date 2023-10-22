package edu.project1;

import edu.hw1.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Project1Test {
    @Test
    @DisplayName("Проверка на задание пустого словаря")
    void emptyDict() {
        // given

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Dictionary(new HashSet<>()),
            "Expected Dictionary constructor with empty set to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Word dictionary cannot be empty");
    }

    @Test
    @DisplayName("Проверка на задание словаря с некорректными вхождениями")
    void incorrectDict() {
        // given

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Dictionary(new HashSet<>(Set.of("good_word", ""))),
            "Expected Dictionary constructor with bad set to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Dictionary cannot have empty words");
    }

    @Test
    @DisplayName("Передача пустого слова в GuessResolver")
    void incorrectGuessResolverEntry() {
        // given

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new GuessResolver("".toCharArray()),
            "Expected GuessResolver constructor with bad char array to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "A guessed word cannot be empty.\nBetter use Dictionary object's getRandomWord() method");
    }

    @Test
    @DisplayName("Попытка задать игру с отрицательным числом ошибок")
    void negativeNumberOfMistakesGame() {
        // given

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Game(new Dictionary(new HashSet<>(Set.of("test"))), -1),
            "Expected Game constructor with negative number of mistakes to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "The maximum number of mistakes should be positive or zero");
    }
}
