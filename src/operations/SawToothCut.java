package operations;

import imageProcessing.processors.Processor;
import models.Image;
import models.Pixel;
import java.awt.image.BufferedImage;

public class SawToothCut implements Processor {
    private int toothSize;

    public SawToothCut(int sawtooth) {
        this.toothSize = 256 / sawtooth;
    }

    @Override
    public void process(Image destinationImage) {
        BufferedImage bufferedImage = destinationImage.getBufferedImage();
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                Pixel pixel = new Pixel(bufferedImage.getRGB(i, j));
                pixel.setRed((int)(((double)(pixel.getRed() % toothSize)/ toothSize) * 255));
                pixel.setGreen((int)(((double)(pixel.getGreen() % toothSize)/ toothSize) * 255));
                pixel.setBlue((int)(((double)(pixel.getBlue() % toothSize)/ toothSize) * 255));
                bufferedImage.setRGB(i, j, pixel.toInteger());
            }
        }
    }
}
