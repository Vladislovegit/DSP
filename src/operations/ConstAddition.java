package operations;

import imageProcessing.processors.OneImageProcessor;
import models.Pixel;

public class ConstAddition extends OneImageProcessor {
    Double constant = 0.0;

    public ConstAddition(Double a) {
        constant = a;
    }

    @Override
    protected int operation(int rgb) {
        Pixel pixel = new Pixel(rgb);
        pixel.setRed((int)(pixel.getRed() + constant));
        pixel.setGreen((int)(pixel.getGreen() + constant));
        pixel.setBlue((int)(pixel.getBlue() + constant));
        return pixel.toInteger();
    }
}
