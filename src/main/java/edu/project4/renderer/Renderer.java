package edu.project4.renderer;

import edu.project4.FractalImage;
import edu.project4.Transformation;
import edu.project4.utils.Rect;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry,
        long seed
    );
}
