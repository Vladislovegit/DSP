package operations;

import imageProcessing.processors.OneImageProcessor;
import models.Pixel;

public class NOT extends OneImageProcessor {

    @Override
    protected int operation(int rgb) {
        Pixel pixel = new Pixel(rgb);
        pixel.setRed(~pixel.getRed() & 0x0f);
        pixel.setGreen(~pixel.getGreen() & 0x0f);
        pixel.setBlue(~pixel.getBlue() & 0x0f);
        return pixel.toInteger();
    }
}
