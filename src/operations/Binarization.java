package operations;

import imageProcessing.processors.OneImageProcessor;
import models.Pixel;

public class Binarization extends OneImageProcessor {

    private Integer lowerLimit;

    public Binarization(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    @Override
    protected int operation(int rgb) {
        Pixel pixel = new Pixel(rgb);
        if(pixel.getBrightness() < lowerLimit) return 0;
        else return new Pixel(255, 255, 255).toInteger();
    }
}
