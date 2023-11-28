package edu.project4;

import edu.project4.utils.Point;
import edu.project4.utils.Rect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Project4Test {
    @Test
    @DisplayName("Проверка на неверные аргументы 1")
    void illegalArgs1() {
        // given
        int width = 0;
        int height = 800;
        int symmetry = 4;
        int samples = 10000;
        int iterPerSample = 100;
        long seed = System.currentTimeMillis();
        Rect world = new Rect(-1.0, -1.0, 2.0, 2.0);

        List<Transformation> variations = new ArrayList<>();
        variations.add(point -> new Point(point.x() * 0.5 + 0.5, point.y() * 0.5 + 0.5));
        variations.add(point -> new Point(point.x() * 0.5 + 0.5, point.y() * 0.5));
        variations.add(point -> new Point(point.x() * 0.5, point.y() * 0.5 + 0.5));

        double gamma = 2.2;
        Path path = Path.of("fractal_image.png");
        ImageFormat format = ImageFormat.PNG;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> FractalFlameGenerator.generate(
                width,
                height,
                symmetry,
                samples,
                iterPerSample,
                seed,
                world,
                variations,
                gamma,
                false,
                path,
                format
            )
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("All size and count parameters should be positive");
    }

    @Test
    @DisplayName("Проверка на неверные аргументы 2")
    void illegalArgs2() {
        // given
        int width = 800;
        int height = 800;
        int symmetry = -2;
        int samples = 10000;
        int iterPerSample = -3;
        long seed = System.currentTimeMillis();
        Rect world = new Rect(-1.0, -1.0, 2.0, 2.0);

        List<Transformation> variations = new ArrayList<>();
        variations.add(point -> new Point(point.x() * 0.5 + 0.5, point.y() * 0.5 + 0.5));
        variations.add(point -> new Point(point.x() * 0.5 + 0.5, point.y() * 0.5));
        variations.add(point -> new Point(point.x() * 0.5, point.y() * 0.5 + 0.5));

        double gamma = 2.2;
        Path path = Path.of("fractal_image.png");
        ImageFormat format = ImageFormat.PNG;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> FractalFlameGenerator.generate(
                width,
                height,
                symmetry,
                samples,
                iterPerSample,
                seed,
                world,
                variations,
                gamma,
                false,
                path,
                format
            )
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("All size and count parameters should be positive");
    }

    @Test
    @DisplayName("Проверка на пустой список")
    void noTransformations() {
        // given
        int width = 800;
        int height = 800;
        int symmetry = 4;
        int samples = 10000;
        int iterPerSample = 100;
        long seed = System.currentTimeMillis();
        Rect world = new Rect(-1.0, -1.0, 2.0, 2.0);

        List<Transformation> variations = new ArrayList<>();

        double gamma = 2.2;
        Path path = Path.of("fractal_image.png");
        ImageFormat format = ImageFormat.PNG;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> FractalFlameGenerator.generate(
                width,
                height,
                symmetry,
                samples,
                iterPerSample,
                seed,
                world,
                variations,
                gamma,
                false,
                path,
                format
            )
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Variation list should not be empty");
    }

    @Test
    @DisplayName("Проверка на несовпадение расширения файла в пути и указанного расширения")
    void extensionMismatch() {
        // given
        int width = 800;
        int height = 800;
        int symmetry = 4;
        int samples = 10000;
        int iterPerSample = 100;
        long seed = System.currentTimeMillis();
        Rect world = new Rect(-1.0, -1.0, 2.0, 2.0);

        List<Transformation> variations = new ArrayList<>();
        variations.add(point -> new Point(point.x() * 0.5 + 0.5, point.y() * 0.5 + 0.5));
        variations.add(point -> new Point(point.x() * 0.5 + 0.5, point.y() * 0.5));
        variations.add(point -> new Point(point.x() * 0.5, point.y() * 0.5 + 0.5));

        double gamma = 2.2;
        Path path = Path.of("fractal_image.jpeg");
        ImageFormat format = ImageFormat.PNG;

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> FractalFlameGenerator.generate(
                width,
                height,
                symmetry,
                samples,
                iterPerSample,
                seed,
                world,
                variations,
                gamma,
                false,
                path,
                format
            )
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Incorrect file extension");
    }
}
