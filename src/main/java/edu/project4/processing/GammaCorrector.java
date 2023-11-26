package edu.project4.processing;

import edu.project4.FractalImage;
import edu.project4.utils.Pixel;

public class GammaCorrector implements ImageProcessor {

    private final double gamma;
    private double max = 0.;

    public GammaCorrector(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(FractalImage image) {
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel.getHitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                    max = Math.max(max, pixel.getNormal());
                }
            }
        }

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                pixel.setNormal(pixel.getNormal() / max);
                pixel.setR((int) (pixel.getR() * Math.pow(pixel.getNormal(), 1. / gamma)));
                pixel.setG((int) (pixel.getG() * Math.pow(pixel.getNormal(), 1. / gamma)));
                pixel.setB((int) (pixel.getB() * Math.pow(pixel.getNormal(), 1. / gamma)));
            }
        }
    }
}
