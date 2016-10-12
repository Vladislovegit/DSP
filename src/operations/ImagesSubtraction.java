package operations;

import imageProcessing.processors.TwoImagesProcessor;
import models.Image;
import models.Pixel;

public class ImagesSubtraction extends TwoImagesProcessor {

    public ImagesSubtraction(Image image) {
        super(image);
    }

    @Override
    protected int operation(int destination, int source) {
        Pixel dest = new Pixel(destination);
        Pixel sour = new Pixel(source);
        dest.setRed((dest.getRed() - sour.getRed()));
        dest.setGreen((dest.getGreen() - sour.getGreen()));
        dest.setBlue((dest.getBlue() - sour.getBlue()));
        return dest.toInteger();
    }
}
