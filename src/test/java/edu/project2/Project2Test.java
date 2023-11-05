package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Project2Test {
    @Test
    @DisplayName("Проверка на задание неверных размеров лабиринта 1")
    void invalidSize1() {
        // given

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Maze(-1, 0),
            "Expected to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Height and width must be odd numbers greater than 2");
    }

    @Test
    @DisplayName("Проверка на задание неверных размеров лабиринта 2")
    void invalidSize2() {
        // given

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Maze(7, 8),
            "Expected to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Height and width must be odd numbers greater than 2");
    }

    @Test
    @DisplayName("Проверка на задание неверных координат для решения лабиринта")
    void invalidCoords() {
        // given
        Maze maze = new Maze(13, 17);

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> maze.solveMaze(0, 0, 12, 16),
            "Expected to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Invalid coordinates");
    }

    @Test
    @DisplayName("Проверка на reproducibility лабиринта по сиду")
    void seedReprodMaze() {
        // given
        Maze maze = new Maze(13, 21, 42);
        String expectedMaze = """
            █████████████████████
            █   █       █   █   █
            ███ ███ ███ ███ █ █ █
            █ █ █   █ █   █ █ █ █
            █ █ █ ███ ███ █ █ █ █
            █ █ █ █     █ █   █ █
            █ █ █ █████ █ █ ███ █
            █ █ █ █   █   █   █ █
            █ █ █ █ █ ███ █ ███ █
            █ █ █   █   █ █ █   █
            █ █ ███████ █ ███ █ █
            █           █     █ █
            █████████████████████
            """;

        // when
        String trueMaze = maze.printMaze();

        // then
        assertThat(expectedMaze).isEqualTo(trueMaze);
    }

    @Test
    @DisplayName("Проверка на reproducibility пути в лабиринте по сиду")
    void seedReprodPath() {
        // given
        Maze maze = new Maze(13, 21, 42);
        maze.solveMaze(1, 1, 11, 19);
        String expectedMaze = """
            █████████████████████
            █***█  *****█   █   █
            ███*███*███*███ █ █ █
            █ █*█***█ █***█ █ █ █
            █ █*█*███ ███*█ █ █ █
            █ █*█*█     █*█   █ █
            █ █*█*█████ █*█ ███ █
            █ █*█*█***█  *█   █ █
            █ █*█*█*█*███*█ ███ █
            █ █*█***█***█*█ █***█
            █ █*███████*█*███*█*█
            █  *********█*****█*█
            █████████████████████
            """;

        // when
        String trueMaze = maze.printMaze();

        // then
        assertThat(expectedMaze).isEqualTo(trueMaze);
    }

}
