package operations;

import imageProcessing.processors.TwoImagesProcessor;
import models.Image;
import models.Pixel;

public class ImagesAddition extends TwoImagesProcessor {

    public ImagesAddition(Image image) {
        super(image);
    }

    @Override
    protected int operation(int destination, int source) {
        Pixel dest = new Pixel(destination);
        Pixel sourRGB = new Pixel(source);
        dest.setRed((dest.getRed() + sourRGB.getRed()));
        dest.setGreen((dest.getGreen() + sourRGB.getGreen()));
        dest.setBlue((dest.getBlue() + sourRGB.getBlue()));
        return dest.toInteger();
    }
}
