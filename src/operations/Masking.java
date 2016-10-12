package operations;

import imageProcessing.processors.TwoImagesProcessor;
import models.Image;
import models.Pixel;

public class Masking extends TwoImagesProcessor {

    public Masking(Image image) {
        super(image);
    }

    @Override
    protected int operation(int destinationRGB, int sourceRGB) {
        return (new Pixel(sourceRGB).getBrightness() == 0) ? 0 : destinationRGB;
    }
}
