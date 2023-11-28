package edu.project4;

import edu.project4.utils.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

@SuppressWarnings("RegexpSinglelineJava")
public final class ImageUtils {
    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        int[] rgbData = new int[image.width() * image.height()];
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                rgbData[y * image.width() + x] = new Color(pixel.getR(), pixel.getG(), pixel.getB()).getRGB();
            }
        }
        bufferedImage.setRGB(0, 0, image.width(), image.height(), rgbData, 0, image.width());
        try {
            ImageIO.write(bufferedImage, format.name().toLowerCase(), filename.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
