package edu.project4.renderer;

import edu.project4.FractalImage;
import edu.project4.Transformation;
import edu.project4.utils.Rect;
import java.util.List;

@SuppressWarnings("RegexpSinglelineJava")
public class MultiThreadedRenderer implements Renderer {
    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry,
        long seed
    ) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numThreads];
        int samplesPerThread = samples / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                SingleThreadedRenderer renderer = new SingleThreadedRenderer();
                renderer.render(
                    canvas,
                    world,
                    variations,
                    samplesPerThread,
                    iterPerSample,
                    symmetry,
                    seed + threadIndex
                );
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return canvas;
    }
}
