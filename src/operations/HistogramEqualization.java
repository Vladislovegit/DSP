package operations;

import imageProcessing.processors.Processor;
import models.Image;
import models.Pixel;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class HistogramEqualization implements Processor {

    private Double[] h = new Double[256];

    public HistogramEqualization() {
        Arrays.fill(h, 0.0);
    }

    @Override
    public void process(Image src) {
        BufferedImage bf = src.getBufferedImage();

        for (int i = 0; i < bf.getWidth(); i++) {
            for (int j = 0; j < bf.getHeight(); j++) {
                h[new Pixel(bf.getRGB(i, j)).getBrightness()]++;
            }
        }
        for (int i = 0; i < 255; i++) {
            h[i] = 255 * h[i] / (bf.getWidth() * bf.getHeight());
        }
        for (int i = 1; i < 255; i++) {
            h[i] = h[i - 1] + h[i];
        }
        for (int i = 0; i < bf.getWidth(); i++) {
            for (int j = 0; j < bf.getHeight(); j++) {
                Integer brightness = new Pixel(bf.getRGB(i, j)).getBrightness();
                bf.setRGB(i,
                        j,
                        new Pixel(h[brightness].intValue(),
                        h[brightness].intValue(),
                        h[brightness].intValue())
                                .toInteger());
            }
        }
    }
}
