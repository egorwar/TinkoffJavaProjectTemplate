package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task8Test {
    @Test
    @DisplayName("Матрица не на 8 строк")
    void incorrectRowNum() {
        // given
        var board = new byte[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task8.knightBoardCapture(board),
            "Expected Task8.rotateRight to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("A board should be an 8x8 array");
    }

    @Test
    @DisplayName("Матрица не на 8 столбцов")
    void incorrectColNum() {
        // given
        var board = new byte[][] {
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 1},
            {0, 1, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0}
        };

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task8.knightBoardCapture(board),
            "Expected Task8.rotateRight to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("A board should be an 8x8 array");
    }

    @Test
    @DisplayName("Не каждый столбец длины 8")
    void incorrectAnyColNum() {
        // given
        var board = new byte[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0}
        };

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task8.knightBoardCapture(board),
            "Expected Task8.rotateRight to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("A board should be an 8x8 array");
    }

    @Test
    @DisplayName("Есть значения кроме 0 и 1")
    void incorrectEntry() {
        // given
        var board = new byte[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 2}
        };

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task8.knightBoardCapture(board),
            "Expected Task8.rotateRight to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("A board can only contain numbers of 0 and 1");
    }

    @Test
    @DisplayName("Расстановка на true")
    void cannotCapture() {
        // given
        var board = new byte[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        // when
        boolean isUncapturable = Task8.knightBoardCapture(board);

        // then
        assertThat(isUncapturable).isTrue();
    }

    @Test
    @DisplayName("Расстановка на false")
    void canCapture() {
        // given
        var board = new byte[][] {
            {1, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        // when
        boolean isUncapturable = Task8.knightBoardCapture(board);

        // then
        assertThat(isUncapturable).isFalse();
    }
}
