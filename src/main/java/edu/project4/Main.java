package edu.project4;

import edu.project4.utils.Point;
import edu.project4.utils.Rect;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {

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
        Path path = Path.of("fractal_image.png");
        ImageFormat format = ImageFormat.PNG;

        long timeStart = System.nanoTime();
        FractalFlameGenerator.generate(
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
        );
        long nanoEstimatedTime = System.nanoTime();
        long seq = nanoEstimatedTime - timeStart;
        LOGGER.info("Sequential, nanoseconds:\t" + seq);

        timeStart = System.nanoTime();
        FractalFlameGenerator.generate(
            width,
            height,
            symmetry,
            samples,
            iterPerSample,
            seed,
            world,
            variations,
            gamma,
            true,
            path,
            format
        );
        nanoEstimatedTime = System.nanoTime();
        long par = nanoEstimatedTime - timeStart;
        LOGGER.info("Parallel, nanoseconds:\t\t" + par);
        LOGGER.info("Parallel is faster by:\t\t" + (1. * seq / par));
    }
}
