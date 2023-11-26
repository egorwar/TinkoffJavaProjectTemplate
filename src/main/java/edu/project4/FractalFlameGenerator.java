package edu.project4;

import edu.project4.processing.GammaCorrector;
import edu.project4.processing.ImageProcessor;
import edu.project4.renderer.MultiThreadedRenderer;
import edu.project4.renderer.Renderer;
import edu.project4.renderer.SingleThreadedRenderer;
import edu.project4.utils.Rect;
import java.nio.file.Path;
import java.util.List;

@SuppressWarnings("ParameterNumber")
public class FractalFlameGenerator {

    private FractalFlameGenerator() {
    }

    public static FractalImage generate(
        int width,
        int height,
        int symmetry,
        int samples,
        int iterPerSample,
        long seed,
        Rect world,
        List<Transformation> variations,
        double gamma,
        boolean isParralel,
        Path path,
        ImageFormat format
    ) {

        for (int param : new int[] {width, height, symmetry, samples, iterPerSample}) {
            if (param <= 0) {
                throw new IllegalArgumentException("All size and count parameters should be positive");
            }
        }

        if (variations.isEmpty()) {
            throw new IllegalArgumentException("Variation list should not be empty");
        }

        if (!path.toString().endsWith("." + format.name().toLowerCase())) {
            throw new IllegalArgumentException("Incorrect file extension");
        }

        FractalImage canvas = FractalImage.create(width, height);

        Renderer renderer = (isParralel) ? new MultiThreadedRenderer() : new SingleThreadedRenderer();
        FractalImage renderedImage = renderer.render(canvas, world, variations, samples, iterPerSample, symmetry, seed);

        if (gamma != 0.) {
            ImageProcessor processor = new GammaCorrector(gamma);
            processor.process(renderedImage);
        }

        ImageUtils.save(renderedImage, path, format);

        return renderedImage;
    }

}
