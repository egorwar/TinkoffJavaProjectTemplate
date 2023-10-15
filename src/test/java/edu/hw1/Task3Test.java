package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    @Test
    @DisplayName("Проверка на пустые массивы")
    void emptyArrays() {
        // given
        int inner[] = new int[0];
        int outer[] = new int[0];

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task3.isNestable(inner, outer),
            "Expected Task3.isNestable to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Arrays should not be empty");
    }

    @Test
    @DisplayName("Проверка на неполный внешний массив")
    void degeneratedOuterArray() {
        // given
        int inner[] = new int[] {1, 2, 3, 4};
        int outer[] = new int[] {5};

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task3.isNestable(inner, outer),
            "Expected Task3.isNestable to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "Outer array should have at least two entries, as they act as nesting borders");
    }

    @Test
    @DisplayName("Проверка случая true")
    void trueInput() {
        // given
        int inner[] = new int[] {-1, 2};
        int outer[] = new int[] {5, 3, -1, -7, 0};

        // when
        boolean result = Task3.isNestable(inner, outer);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка случая false")
    void falseInput() {
        // given
        int inner[] = new int[] {-1, 5, 2};
        int outer[] = new int[] {5, 3, -1, -7, 0};

        // when
        boolean result = Task3.isNestable(inner, outer);

        // then
        assertThat(result).isEqualTo(false);
    }
}
